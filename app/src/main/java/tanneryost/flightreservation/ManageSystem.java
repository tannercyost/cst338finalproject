package tanneryost.flightreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageSystem extends AppCompatActivity {
    public static final String TAG = "ManageSystem";

    EditText editAccountName;
    EditText editPassword;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);
        login();

        editAccountName  = (EditText) findViewById(R.id.editAccountName);
        editPassword  = (EditText) findViewById(R.id.editPassword);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(editAccountName) || isEmpty(editPassword)) {
                    Log.i(TAG, "Error - fields must be set");
                    toastMaker("All fields must be set");
                } else {
                    if (checkField(editAccountName.getText().toString())
                            && checkField(editPassword.getText().toString())) {
                        String name = editAccountName.getText().toString();
                        String passwd = editPassword.getText().toString();
                        if (name.equals("admin2") && passwd.equals("admin2")) {
                            toastMaker("Success");
                            //login(view);
                        }
                    } else {
                        toastMaker("Incorrect account name and/or password.");
                    }

                }
            }
        });
    }

    public void login () {
        Intent intent = new Intent(this, ManageSystemLoggedIn.class);
        startActivity(intent);
    }

    private boolean checkField(String field) {
        char[] word = field.toCharArray();
        int character = 0;
        int digit = 0;
        for (char item : word) {
            if (Character.isLetter(item))
                character++;
            else if (Character.isDigit(item))
                digit++;
        }
        if (character >= 3 && digit >= 1)
            return true;
        return false;
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
