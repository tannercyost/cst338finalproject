package tanneryost.flightreservation.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

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
