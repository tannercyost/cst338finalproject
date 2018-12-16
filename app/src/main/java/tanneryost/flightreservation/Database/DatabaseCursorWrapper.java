package tanneryost.flightreservation.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import tanneryost.flightreservation.LogItem;
import tanneryost.flightreservation.AccountItem;

import java.util.Date;
import java.util.UUID;

/**
 * This supplies the mechanism to get a log item from a cursor.
 */

public class DatabaseCursorWrapper extends CursorWrapper {
    public DatabaseCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public LogItem getLogItem() {

        String uuidString = getString(getColumnIndex(DatabaseSchema.GymLogTable.Cols.UUID));
        String exercise = getString(getColumnIndex(DatabaseSchema.GymLogTable.Cols.EXERCISE));

        double weight = getDouble(getColumnIndex(DatabaseSchema.GymLogTable.Cols.WEIGHT));

        int reps = getInt(getColumnIndex(DatabaseSchema.GymLogTable.Cols.REPS));
        int sqlLogId = getInt(getColumnIndex("_id"));

        Date date = new Date(getLong(getColumnIndex(DatabaseSchema.GymLogTable.Cols.DATE)));

        LogItem log = new LogItem(UUID.fromString(uuidString));

        log.setExercize(exercise);
        log.setWeight(weight);
        log.setDate(date);
        log.setReps(reps);
        log.setSqlLogId(sqlLogId);

        return log;
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
