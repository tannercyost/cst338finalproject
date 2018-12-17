package tanneryost.flightreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReserveSeat extends AppCompatActivity {
    TextView tempTextView;
    FlightItem flightItem;
    FlightLog flightLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        tempTextView = (TextView) findViewById(R.id.tempTextView);
        tempTextView.setMovementMethod(new ScrollingMovementMethod());
        flightLog = FlightLog.get(this.getApplicationContext());

        tempTextView.setText(flightLog.getLogString());

    }
}
