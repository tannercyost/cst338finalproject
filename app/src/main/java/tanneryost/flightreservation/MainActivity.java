/**
 * Tanner Yost
 * MainActivity.java
 * Abstract: Main page for Android App. Further "page" java files will not have abstract.
 *
 * Tanner Yost
 * 12/16/2018
 */

package tanneryost.flightreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void reserveSeat(View view) {
        Intent intent = new Intent(this, ReserveSeat.class);
        startActivity(intent);
    }

    public void cancelReservation(View view) {
        Intent intent = new Intent(this, CancelReservation.class);
        startActivity(intent);
    }

    public void manageSystem(View view) {
        Intent intent = new Intent(this, ManageSystem.class);
        startActivity(intent);
    }
}
