package tanneryost.flightreservation.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import tanneryost.flightreservation.Database.DatabaseSchema.FlightTable;
import tanneryost.flightreservation.FlightItem;

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
 * This returns FlightItems, Lists of FlightItems and primitives (longs etc)
 */

public class FlightHelper extends SQLiteOpenHelper {

    private static final String TAG = "FlightLog";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "flightLog.db";

    private SQLiteDatabase db;

    public FlightHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + FlightTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                FlightTable.Cols.NUMBER     + "," +
                FlightTable.Cols.DEPARTURE  + "," +
                FlightTable.Cols.ARRIVAL    + "," +
                FlightTable.Cols.TIME  + "," +
                FlightTable.Cols.CAPACITY  + "," +
                FlightTable.Cols.PRICE  + "," +
                FlightTable.Cols.DATEADDED  + "," +
                FlightTable.Cols.UUID       +
                ")"
        );
        FlightItem acc1 = new FlightItem("Otter101", "Monterey", "Los Angeles", "10:00(AM)", 10, 150.00);
//        FlightItem acc2 = new FlightItem("brian77", "123ABC");
//        FlightItem acc3 = new FlightItem("chris21", "CHRIS21");
        ContentValues cv1 = getContentValues(acc1);
//        ContentValues cv2 = getContentValues(acc2);
//        ContentValues cv3 = getContentValues(acc3);
        db.insert(FlightTable.NAME, null, cv1);
//        db.insert(FlightTable.NAME, null, cv2);
//        db.insert(FlightTable.NAME, null, cv3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NYI
    }

    private long insertFlight(FlightItem Flight) {
        ContentValues cv = getContentValues(Flight);

        db = this.getWritableDatabase();

        return db.insert(FlightTable.NAME, null, cv);
    }

    public long addFlightItem(FlightItem Flight) {
        if(this.getFlightItem(Flight.getIDnum()) == null) {
            return insertFlight(Flight);
        } else {
            return updateFlightItem(Flight);
        }
    }

    private int updateFlightItem(FlightItem Flight) {
        db = this.getWritableDatabase();
        ContentValues cv = FlightHelper.getContentValues(Flight);
        String whereClause = FlightTable.Cols.UUID + " = ? ";
        String[] whereArgs = {Flight.getIDnum().toString()};
        try{
            return db.update(FlightTable.NAME, cv, whereClause, whereArgs);
        } catch (Exception e) {
            Log.d(TAG, "something is wrong in updateFlightItem");
            return -1;
        }
    }

    private FlightItem getFlightItem(UUID logUUID) {

        String whereClause = FlightTable.Cols.UUID + " = ? ";
        String[] whereArgs = {logUUID.toString()};

        DatabaseCursorWrapper cursor = new DatabaseCursorWrapper(this.queryDB(FlightTable.NAME,whereClause,whereArgs));

        try {
            if (cursor.getCount() == 0) {
                Log.d(TAG, "No results from getFlightItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getFlightItem();
        } finally {
            cursor.close();
        }
    }

    public List<FlightItem> getLogs() {
        List<FlightItem> logs = new ArrayList<>();
        DatabaseCursorWrapper cursor = new DatabaseCursorWrapper(this.queryDB(FlightTable.NAME,null,null));
        try {
            if(cursor.getCount() == 0) {
                Log.d(TAG, "getFlightItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()) {
                Log.i("FlightLog", cursor.getFlightItem().toString());
                logs.add(cursor.getFlightItem());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return logs;
    }

    private Cursor queryDB(String DBName, String whereClause, String[] whereArgs) {
        db = this.getWritableDatabase();

        try {
            return db.query(FlightTable.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null);
        } catch (Exception e) {
            Log.d(TAG, "Problem in queryDB!!");
            return null;
        }
    }

    public static ContentValues getContentValues(FlightItem log) {
        ContentValues cv = new ContentValues();

        cv.put(FlightTable.Cols.UUID, log.getIDnum().toString());
        cv.put(FlightTable.Cols.NUMBER, log.getNumber());
        cv.put(FlightTable.Cols.DEPARTURE, log.getDeparture());
        cv.put(FlightTable.Cols.ARRIVAL, log.getArrival());
        cv.put(FlightTable.Cols.TIME, log.getTime());
        cv.put(FlightTable.Cols.CAPACITY, log.getCapacity());
        cv.put(FlightTable.Cols.PRICE, log.getPrice());
        cv.put(FlightTable.Cols.DATEADDED, log.getDateAdded().toString());

        return cv;
    }
}
