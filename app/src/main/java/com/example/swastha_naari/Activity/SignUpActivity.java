package com.example.swastha_naari.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swastha_naari.Models.Login;
import com.example.swastha_naari.R;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.usernameid)
    EditText usernameId;

    @BindView(R.id.passwordid)
    EditText passwordId;

    @BindView(R.id.repasswordid)
    EditText ReenterPassword;

    @BindView(R.id.signUp)
    Button signUpBtn;

    @BindView(R.id.login)
    TextView loginLink;

    Realm realm;
    private Login userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void signup(){
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        signUpBtn.setEnabled(false);

//        final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Creating Account...");
//        progressDialog.show();


        String username = usernameId.getText().toString();
        String password = passwordId.getText().toString();
        String reEnterPassword = ReenterPassword.getText().toString();

        realm.beginTransaction();
        Login userCredentials = realm.createObject(Login.class, UUID.randomUUID().toString());

        userCredentials.setUsername(username);
        userCredentials.setPassword(password);
        realm.commitTransaction();

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
//                        progressDialog.dismiss();
                        // onSignupFailed();

                    }
                }, 3000);


    }


    public void onSignupSuccess() {
        signUpBtn.setEnabled(true);
        setResult(RESULT_OK, null);

        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
//        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        signUpBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = usernameId.getText().toString();
        String password = passwordId.getText().toString();
        String reEnterPassword = ReenterPassword.getText().toString();

        if (username.isEmpty() || username.length() < 3) {
            usernameId.setError("at least 3 characters");
            valid = false;
        } else {
            usernameId.setError(null);
        }


        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordId.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordId.setError(null);
        }

        if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
            ReenterPassword.setError("Password Do not match");
            valid = false;
        } else {
            ReenterPassword.setError(null);
        }

        return valid;
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
