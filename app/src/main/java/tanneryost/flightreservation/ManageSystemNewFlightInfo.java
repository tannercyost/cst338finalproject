/**
 * Tanner Yost
 * ManageSystemNewFlightInfo.java
 * Abstract: Allows admin2 user to add a new flight to the system.
 * Date: 12/16/2018
 */
package tanneryost.flightreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ManageSystemNewFlightInfo extends AppCompatActivity {
    EditText number;
    EditText departure;
    EditText arrival;
    EditText time;
    EditText capacity;
    EditText price;
    Button submitButton;

    //database items
    FlightItem flightItem;
    FlightLog flightLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system_new_flight_info);

        number = (EditText) findViewById(R.id.number);
        departure = (EditText) findViewById(R.id.departure);
        arrival = (EditText) findViewById(R.id.arrival);
        time = (EditText) findViewById(R.id.time);
        capacity = (EditText) findViewById(R.id.capacity);
        price = (EditText) findViewById(R.id.price);
        submitButton = (Button) findViewById(R.id.submitButton);
        flightLog = FlightLog.get(this.getApplicationContext());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(number) || isEmpty(departure) || isEmpty(arrival) || isEmpty(time) || isEmpty(capacity) || isEmpty(price)) {
                    toastMaker("All fields must be set to continue.");
                } else {
                    String num = number.getText().toString();
                    String dep = departure.getText().toString();
                    String arr = arrival.getText().toString();
                    String ti = time.getText().toString();
                    int cap = Integer.parseInt(capacity.getText().toString());
                    double pri = Double.parseDouble(price.getText().toString());
                    FlightItem flight = createFlightItem(num, dep, arr, ti, cap, pri);
                    List<FlightItem> flights = flightLog.getFlightList();
                    boolean found = false;
                    for (FlightItem item : flights) {
                        if (item.getNumber().equals(num))
                            found = true;
                    }
                    if (!found)
                        flightLog.addLog(flight);
                    else {
                        toastMaker("Flight with number " + num + " already exists");
                    }
                    finish();
                }
            }
        });

    }


    private FlightItem createFlightItem(String number, String departure, String arrival, String time, int capacity, double price) {
        FlightItem flight = new FlightItem(number, departure, arrival, time, capacity, price);
        return flight;
    }

    private void toastMaker(String message) {
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }

    private boolean isEmpty(EditText textToCheck) {
        return textToCheck.getText().toString().trim().length() == 0;
    }
}
