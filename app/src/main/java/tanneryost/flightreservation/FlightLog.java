/**
 * Tanner Yost
 * FlightLog.java
 * Abstract: Adapted from GymLog, keeps a log of all flights.
 * Date: 12/16/2018
 */
package tanneryost.flightreservation;

import android.content.Context;

import java.util.List;

import tanneryost.flightreservation.Database.FlightHelper;

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

    public List<FlightItem> getFlightList() {
        return mFlightHelper.getLogs();
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
