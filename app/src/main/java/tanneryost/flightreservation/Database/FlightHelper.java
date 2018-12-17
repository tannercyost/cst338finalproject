/**
 * Tanner Yost
 * FlightHelper.java
 * Abstract: Adapted from GymLog, database helper for flight items.
 * Date: 12/16/2018
 */

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
        FlightItem flight1 = new FlightItem("Otter101", "Monterey", "Los Angeles", "10:00(AM)", 10, 150.00);
        FlightItem flight2 = new FlightItem("Otter102", "Los Angeles", "Monterey", "01:00(PM)", 10, 150.00);
        FlightItem flight3 = new FlightItem("Otter201", "Monterey", "Seattle", "11:00(AM)", 5, 200.50);
        FlightItem flight4 = new FlightItem("Otter205", "Monterey", "Seattle", "03:00(PM)", 15, 150.00);
        FlightItem flight5 = new FlightItem("Otter202", "Seattle", "Monterey", "02:00(PM)", 5, 200.50);

        ContentValues cv1 = getContentValues(flight1);
        ContentValues cv2 = getContentValues(flight2);
        ContentValues cv3 = getContentValues(flight3);
        ContentValues cv4 = getContentValues(flight4);
        ContentValues cv5 = getContentValues(flight5);

        db.insert(FlightTable.NAME, null, cv1);
        db.insert(FlightTable.NAME, null, cv2);
        db.insert(FlightTable.NAME, null, cv3);
        db.insert(FlightTable.NAME, null, cv4);
        db.insert(FlightTable.NAME, null, cv5);
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
