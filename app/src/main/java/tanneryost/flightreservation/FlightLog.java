package tanneryost.flightreservation;

import android.content.Context;

import java.util.List;

import tanneryost.flightreservation.Database.FlightHelper;

/**
 * This is the file that we use to read/write to the DB.
 * This creates an instance of the helper and we send our requests here.
 * Any data massaging happens here.
 */

public class FlightLog {
    private static FlightLog sFlightLog;
    private Context mContext;
    private FlightHelper mFlightHelper;

    public static FlightLog get(Context context) {
        if(sFlightLog == null) {
            sFlightLog = new FlightLog(context);
        }
        return sFlightLog;
    }

    private FlightLog(Context context) {
        mContext = context.getApplicationContext();
        mFlightHelper = new FlightHelper(mContext);
    }

    public long addLog(FlightItem Flight) {
        return mFlightHelper.addFlightItem(Flight);
    }

    public String getLogString() {
        StringBuilder sb = new StringBuilder();
        List<FlightItem> logs = mFlightHelper.getLogs();
        if (logs == null) {
            return "Flight Logs null\n";
        }

        sb.append("Flight Logs:\n");

        for(FlightItem log : logs) {
            sb.append(log.toString());
        }

        return sb.toString();
    }
}
