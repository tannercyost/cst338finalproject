package tanneryost.flightreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    EditText editAccountName;
    EditText editPassword;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editAccountName  = (EditText) findViewById(R.id.editAccountName);
        editPassword  = (EditText) findViewById(R.id.editPassword);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toastMaker(editAccountName.getText().toString() + editPassword.getText().toString());
            }
        });
    }

    private void toastMaker(String message){
        Toast t = Toast.makeText(this.getApplicationContext(),message,Toast.LENGTH_LONG );
        t.setGravity(Gravity.CENTER_VERTICAL,0,0);
        t.show();
    }

    private boolean isEmpty(EditText textToCheck){
        return textToCheck.getText().toString().trim().length() == 0;
    }
}
