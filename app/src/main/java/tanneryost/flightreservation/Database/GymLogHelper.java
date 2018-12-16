package tanneryost.flightreservation.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import tanneryost.flightreservation.Database.DatabaseSchema.GymLogTable;
import tanneryost.flightreservation.LogItem;

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
 * This returns LogItems, Lists of LogItems and primitives (longs etc)
 */

public class GymLogHelper extends SQLiteOpenHelper {

    private static final String TAG = "GYM_Log";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "gymlog.db";

    private SQLiteDatabase db;

    public GymLogHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table " + GymLogTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                                    GymLogTable.Cols.DATE + ","+
                                    GymLogTable.Cols.EXERCISE + ","+
                                    GymLogTable.Cols.REPS + ","+
                                    GymLogTable.Cols.UUID + ","+
                                    GymLogTable.Cols.WEIGHT +
                                    ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    private long insertLog(LogItem log){

        ContentValues cv = getContentValues(log);

        db = this.getWritableDatabase();

        return db.insert(GymLogTable.NAME, null, cv);

    }

    public long addLogItem(LogItem log){

        if(this.getLogItem(log.getLogID()) == null){
            return insertLog(log);
        } else {
            return updateLogItem(log);
        }
    }

    private int updateLogItem(LogItem log){
        db = this.getWritableDatabase();
        ContentValues cv = GymLogHelper.getContentValues(log);
        String whereClause = GymLogTable.Cols.UUID + " = ? ";
        String[] whereArgs = {log.getLogID().toString()};
        try{
            return db.update(GymLogTable.NAME, cv, whereClause, whereArgs);
        } catch (Exception e){
            Log.d(TAG, "something is wrong in updateLogItem");
            return -1;
        }
    }

    private LogItem getLogItem(UUID logUUID){

        String whereClause = GymLogTable.Cols.UUID + " = ? ";
        String[] whereArgs = {logUUID.toString()};

        GymLogCursorWrapper cursor = new GymLogCursorWrapper(this.queryDB(GymLogTable.NAME,whereClause,whereArgs));

        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getLogItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLogItem();
        } finally {
            cursor.close();
        }
    }

    public List<LogItem> getLogs(){
        List<LogItem> logs = new ArrayList<>();

        GymLogCursorWrapper cursor = new GymLogCursorWrapper(this.queryDB(GymLogTable.NAME,null,null));
        try{
            if(cursor.getCount() == 0){
                Log.d(TAG, "getLogItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                logs.add(cursor.getLogItem());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }

        return logs;
    }

    private Cursor queryDB(String DBName, String whereClause, String[] whereArgs){
        db = this.getWritableDatabase();

        try{
            return db.query(GymLogTable.NAME,
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

    public static ContentValues getContentValues(LogItem log){
        ContentValues cv = new ContentValues();
        cv.put(GymLogTable.Cols.DATE, log.getDate().getTime());
        cv.put(GymLogTable.Cols.EXERCISE, log.getExercize());
        cv.put(GymLogTable.Cols.REPS, log.getReps());
        cv.put(GymLogTable.Cols.UUID, log.getLogID().toString());
        cv.put(GymLogTable.Cols.WEIGHT, log.getWeight());

        return cv;
    }

}
