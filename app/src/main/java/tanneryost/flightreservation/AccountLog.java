/**
 * Tanner Yost
 * AccountLog.java
 * Abstract: Adapted from GymLog, keeps a log of all accounts.
 * Date: 12/16/2018
 */

package tanneryost.flightreservation;

import android.content.Context;

import java.util.List;

import tanneryost.flightreservation.Database.AccountHelper;


public class AccountLog {
    private static AccountLog sAccountLog;
    private Context mContext;
    private AccountHelper mAccountHelper;

    public static AccountLog get(Context context) {
        if(sAccountLog == null) {
            sAccountLog = new AccountLog(context);
        }
        return sAccountLog;
    }

    private AccountLog(Context context) {
        mContext = context.getApplicationContext();
        mAccountHelper = new AccountHelper(mContext);
    }

    public long addLog(AccountItem account) {
        return mAccountHelper.addAccountItem(account);
    }

    public List<AccountItem> getAccountList() {
        return mAccountHelper.getLogs();
    }

    public String getLogString() {
        StringBuilder sb = new StringBuilder();
        List<AccountItem> logs = mAccountHelper.getLogs();
        if (logs == null) {
            return "Account Logs null\n";
        }

        sb.append("Account Logs:\n");

        for(AccountItem log : logs) {
            sb.append(log.toString());
        }

        return sb.toString();
    }
}
