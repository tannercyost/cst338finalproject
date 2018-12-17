package tanneryost.flightreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ManageSystemLoggedIn extends AppCompatActivity {
    TextView accountData;
    TextView flightData;

    AccountItem accountItem;
    AccountLog accountLog;

    FlightItem flightItem;
    FlightLog flightLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system_logged_in);
        accountData = (TextView) findViewById(R.id.accountData);
        flightData = (TextView) findViewById(R.id.flightData);
        accountData.setMovementMethod(new ScrollingMovementMethod());
        flightData.setMovementMethod(new ScrollingMovementMethod());

        accountLog = AccountLog.get(this.getApplicationContext());
        flightLog = FlightLog.get(this.getApplicationContext());

        List<FlightItem> flights = flightLog.getFlightList();
        List<AccountItem> accounts = accountLog.getAccountList();

        for (AccountItem item : accounts) {
            accountData.append("\n");
            accountData.append("Name: ");
            accountData.append(item.getName());
            accountData.append("\n");
            accountData.append("Date created: \n");
            accountData.append(item.getDateString());
            accountData.append("\n");
        }
        accountData.append("\n\n\n");
        for (FlightItem item : flights) {
            flightData.append("\n");
            flightData.append("Flight number:\n");
            flightData.append(item.getNumber());
            flightData.append("\nDeparture:\n");
            flightData.append(item.getDeparture());
            flightData.append("\nArrival:\n");
            flightData.append(item.getArrival());
            flightData.append("\nDeparture Time:\n");
            flightData.append(item.getTime());
            flightData.append("\nFlight Capacity:\n");
            flightData.append(Integer.toString(item.getCapacity()));
            flightData.append("\nFlight Price:\n");
            flightData.append(Double.toString(item.getPrice()).format("$%.2f",item.getPrice()));
            flightData.append("\nDate created:\n");
            flightData.append(item.getDateString());
            flightData.append("\n");
        }
        flightData.append("\n\n\n");
    }

    public void addFlightInfo(View view) {
        Intent intent = new Intent(this, ManageSystemNewFlightInfo.class);
        startActivity(intent);
    }
}
