package tanneryost.flightreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReserveSeat extends AppCompatActivity {
    TextView info;

    //database items
    FlightItem flightItem;
    FlightLog flightLog;
    AccountItem accountItem;
    AccountLog accountLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

    }
}
