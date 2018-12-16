package tanneryost.flightreservation;

import android.content.Intent;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    public static final String TAG = "AccountLog";
    EditText editAccountName;
    EditText editPassword;
    Button submitButton;
//    TextView tempTextView;

    //database items
    AccountItem accountItem;
    AccountLog accountLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

//        tempTextView = (TextView) findViewById(R.id.tempTextView);
//        tempTextView.setMovementMethod(new ScrollingMovementMethod());

        editAccountName  = (EditText) findViewById(R.id.editAccountName);
        editPassword  = (EditText) findViewById(R.id.editPassword);
        submitButton = (Button) findViewById(R.id.submitButton);
        accountLog = AccountLog.get(this.getApplicationContext());
//        tempTextView.setText(accountLog.getLogString());

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

                        if(accountLog.getLogString().contains(name) || name.equals("admin2")) {
                            Log.i(TAG, "Account " + name + " already exists.");
                            toastMaker("Account " + name + " already exists.");
                        } else {
                            accountItem = createAccountItem(name, passwd);
                            Log.i(TAG, "AccountItem: " + accountItem.toString());
                            accountLog.addLog(accountItem);
                            toastMaker("Account " + accountItem.getName() + " created successfully");
                            finish();
                        }
                    } else {
                        toastMaker("Account and password must contain at least 1 number and 3 characters.");
                    }

                }
//                tempTextView.setText(accountLog.getLogString());
            }
        });
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
        else
            return false;
    }

    private AccountItem createAccountItem(String name, String passwd) {
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
