package tanneryost.flightreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {
    public static final String TAG = "AccountLog";
    EditText editAccountName;
    EditText editPassword;
    Button submitButton;
    TextView tempTextView;

    //database items
    AccountItem accountItem;
    AccountLog accountLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        tempTextView = (TextView) findViewById(R.id.tempTextView);
        tempTextView.setMovementMethod(new ScrollingMovementMethod());

        editAccountName  = (EditText) findViewById(R.id.editAccountName);
        editPassword  = (EditText) findViewById(R.id.editPassword);
        submitButton = (Button) findViewById(R.id.submitButton);
        accountLog = AccountLog.get(this.getApplicationContext());
        tempTextView.setText(accountLog.getLogString());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEmpty(editAccountName) || isEmpty(editPassword)) {
                    Log.i(TAG, "Error - fields must be set");
                    toastMaker("All fields must be set");
                } else {
                    accountItem = createAccountItem();
                    Log.i(TAG, "AccountItem: " + accountItem.toString());
                    accountLog.addLog(accountItem);
                }
                tempTextView.setText(accountLog.getLogString());
            }
        });
    }

    private AccountItem createAccountItem() {
        String name = editAccountName.getText().toString();
        String passwd = editPassword.getText().toString();

        AccountItem acc = new AccountItem();

        acc.setName(name);
        acc.setPasswd(passwd);

        return acc;
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
