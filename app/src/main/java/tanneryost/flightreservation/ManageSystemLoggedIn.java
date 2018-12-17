package tanneryost.flightreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageSystemLoggedIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system_logged_in);
    }

    public void addFlightInfo(View view) {
        Intent intent = new Intent(this, ManageSystemNewFlightInfo.class);
        startActivity(intent);
    }
}
