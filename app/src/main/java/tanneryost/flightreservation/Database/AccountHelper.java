package tanneryost.flightreservation.Database;

import android.accounts.Account;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import tanneryost.flightreservation.Database.DatabaseSchema.AccountTable;
import tanneryost.flightreservation.AccountItem;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class is where the database is created and accessed. We design this to be encapsulated.
 * We NEVER access this directly.
 *
 * We use the get static method to get an object ensuring that only one ever exists.
 * This, it turns out, is a singleton design pattern.
 *
 * This returns AccountItems, Lists of AccountItems and primitives (longs etc)
 */

public class AccountHelper extends SQLiteOpenHelper {

    private static final String TAG = "AccountLog";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "accountLog.db";

    private SQLiteDatabase db;

    public AccountHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + AccountTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                AccountTable.Cols.NAME + ","+
                AccountTable.Cols.PASSWD + ","+
                AccountTable.Cols.DATE + ","+
                AccountTable.Cols.UUID +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //NYI
    }

    private long insertAccount(AccountItem account) {

        ContentValues cv = getContentValues(account);

        db = this.getWritableDatabase();

        return db.insert(AccountTable.NAME, null, cv);

    }

    public long addAccountItem(AccountItem account) {
        if(this.getAccountItem(account.getIDnum()) == null) {
            return insertAccount(account);
        } else {
            return updateAccountItem(account);
        }
    }

    private int updateAccountItem(AccountItem account) {
        db = this.getWritableDatabase();
        ContentValues cv = AccountHelper.getContentValues(account);
        String whereClause = AccountTable.Cols.UUID + " = ? ";
        String[] whereArgs = {account.getIDnum().toString()};
        try{
            return db.update(AccountTable.NAME, cv, whereClause, whereArgs);
        } catch (Exception e) {
            Log.d(TAG, "something is wrong in updateAccountItem");
            return -1;
        }
    }

    private AccountItem getAccountItem(UUID logUUID) {

        String whereClause = AccountTable.Cols.UUID + " = ? ";
        String[] whereArgs = {logUUID.toString()};

        DatabaseCursorWrapper cursor = new DatabaseCursorWrapper(this.queryDB(AccountTable.NAME,whereClause,whereArgs));

        try {
            if (cursor.getCount() == 0) {
                Log.d(TAG, "No results from getAccountItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getAccountItem();
        } finally {
            cursor.close();
        }
    }

    public List<AccountItem> getLogs(){
        List<AccountItem> logs = new ArrayList<>();
        DatabaseCursorWrapper cursor = new DatabaseCursorWrapper(this.queryDB(AccountTable.NAME,null,null));
        try {
            if(cursor.getCount() == 0){
                Log.d(TAG, "getAccountItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                logs.add(cursor.getAccountItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return logs;
    }

    private Cursor queryDB(String DBName, String whereClause, String[] whereArgs){
        db = this.getWritableDatabase();

        try{
            return db.query(AccountTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null);
        }catch (Exception e){
            Log.d(TAG, "Problem in queryDB!!");
            return null;
        }
    }

    public static ContentValues getContentValues(AccountItem log){
        ContentValues cv = new ContentValues();
        cv.put(AccountTable.Cols.UUID, log.getIDnum().toString());
        cv.put(AccountTable.Cols.NAME, log.getName().toString());
        cv.put(AccountTable.Cols.DATE, log.getDate().toString());
        cv.put(AccountTable.Cols.PASSWD, log.getPasswd().toString());
        return cv;
    }

}
