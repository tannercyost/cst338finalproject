/**
 * Tanner Yost
 * ReserveSeat.java
 *
 * Date: 12/16/2018
 */

package tanneryost.flightreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReserveSeat extends AppCompatActivity {
    Spinner departure;
    Spinner arrival;
    Button submitButton;
    TextView results;
    TextView numTickets;

    //database items
    FlightItem flightItem;
    FlightLog flightLog;
    AccountItem accountItem;
    AccountLog accountLog;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        accountLog = AccountLog.get(this.getApplicationContext());
        flightLog = FlightLog.get(this.getApplicationContext());
        departure = (Spinner) findViewById(R.id.departure);
        arrival = (Spinner) findViewById(R.id.arrival);
        submitButton = (Button) findViewById(R.id.submitButton);
        results = (TextView) findViewById(R.id.results);
        results.setMovementMethod(new ScrollingMovementMethod());
        numTickets = (EditText) findViewById(R.id.numOfTickets);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                results.setText("");
                String depart = String.valueOf(departure.getSelectedItem());
                String arr = String.valueOf(arrival.getSelectedItem());
                int numberTickets = Integer.parseInt(numTickets.getText().toString());
                //toastMaker(depart + " " + arr);
                List<FlightItem> flights = flightLog.getFlightList();
                List<AccountItem> accounts = accountLog.getAccountList();
                List<FlightItem> foundFlights = new ArrayList<>();
                for (FlightItem item : flights) {
                    if (item.getDeparture().equals(depart) && item.getArrival().equals(arr)) {
                        foundFlights.add(item);
                    }
                }
                if(foundFlights.isEmpty()) {
                    results.setText("No results were found.");
                } else {
                    for (FlightItem item : foundFlights) {
                        results.append("\n");
                        results.append("Flight number:\n");
                        results.append(item.getNumber());
                        results.append("\nDeparture:\n");
                        results.append(item.getDeparture());
                        results.append("\nArrival:\n");
                        results.append(item.getArrival());
                        results.append("\nDeparture Time:\n");
                        results.append(item.getTime());
                        results.append("\nFlight Capacity:\n");
                        results.append(Integer.toString(item.getCapacity()));
                        results.append("\nFlight Price:\n");
                        results.append(Double.toString(item.getPrice()).format("$%.2f",item.getPrice()));
                        results.append("\n");                    }
                }
            }
        });
    }

    private void toastMaker(String message) {
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }
}
