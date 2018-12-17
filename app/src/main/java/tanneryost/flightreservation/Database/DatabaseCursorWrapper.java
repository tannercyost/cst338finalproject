package tanneryost.flightreservation.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import tanneryost.flightreservation.AccountItem;
import tanneryost.flightreservation.FlightItem;

import java.util.Date;
import java.util.UUID;

/**
 * This supplies the mechanism to get a log item from a cursor.
 */

public class DatabaseCursorWrapper extends CursorWrapper {
    public DatabaseCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public FlightItem getFlightItem() {
        String uuidString = getString(getColumnIndex(DatabaseSchema.FlightTable.Cols.UUID));
        String number = getString(getColumnIndex(DatabaseSchema.FlightTable.Cols.NUMBER));
        String departure = getString(getColumnIndex(DatabaseSchema.FlightTable.Cols.DEPARTURE));
        String arrival = getString(getColumnIndex(DatabaseSchema.FlightTable.Cols.ARRIVAL));
        String time = getString(getColumnIndex(DatabaseSchema.FlightTable.Cols.TIME));
        int capacity = getInt(getColumnIndex(DatabaseSchema.FlightTable.Cols.CAPACITY));
        double price = getDouble(getColumnIndex(DatabaseSchema.FlightTable.Cols.PRICE));
        Date date = new Date(getString(getColumnIndex(DatabaseSchema.FlightTable.Cols.DATEADDED)));
        int sqlLogId = getInt(getColumnIndex("_id"));

        FlightItem flight = new FlightItem(UUID.fromString(uuidString));

        flight.setNumber(number);
        flight.setDeparture(departure);
        flight.setArrival(arrival);
        flight.setTime(time);
        flight.setCapacity(capacity);
        flight.setPrice(price);
        flight.setSqlLogId(sqlLogId);
        flight.setDateAdded(date);

        return flight;
    }

    public AccountItem getAccountItem() {

        String uuidString = getString(getColumnIndex(DatabaseSchema.AccountTable.Cols.UUID));
        String name = getString(getColumnIndex(DatabaseSchema.AccountTable.Cols.NAME));
        String passwd = getString(getColumnIndex(DatabaseSchema.AccountTable.Cols.PASSWD));
        int sqlLogId = getInt(getColumnIndex("_id"));
        Date date = new Date(getString(getColumnIndex(DatabaseSchema.AccountTable.Cols.DATE)));
        AccountItem acc = new AccountItem(UUID.fromString(uuidString));

        acc.setPasswd(passwd);
        acc.setName(name);
        acc.setDate(date);
        acc.setSqlLogId(sqlLogId);

        return acc;
    }
}
