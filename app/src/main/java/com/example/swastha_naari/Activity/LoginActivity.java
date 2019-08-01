package com.example.swastha_naari.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swastha_naari.Models.Login;
import com.example.swastha_naari.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @BindView(R.id.usernameid)
    EditText usernameId;

    @BindView(R.id.passwordid)
    EditText passwordId;

    @BindView(R.id.login)
    Button loginBtn;

//    @BindView(R.id.log)
//    TextView log;

    @BindView(R.id.signUp)
    TextView signUpLink;

    Realm realm;
    Boolean loginbool;

    public static SharedPreferences loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        loggedIn = getSharedPreferences("logged_in", MODE_PRIVATE);
        if (loggedIn.getBoolean("loggedIn", false)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }

        Realm.init(this);
        realm = Realm.getDefaultInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
//                Intent intent=new Intent(MainActivity.this, AddNoteActivity.class);
//                startActivity(intent);
//                if (usernameId.length() == 0){
//                    showSnackBar("Enter username");
//                    usernameId.requestFocus();
//                } else if (passwordId.length() == 0){
//                    showSnackBar("Enter Password");
//                    passwordId.requestFocus();
//                }
//                showData();
            }

//            private boolean checkUser(String username, String password){
//                RealmResults<Login> realmObjects = realm.where(Login.class).findAll();
//                for (Login userDetails : realmObjects) {
//                    if (username.equals(userDetails.getUsername()) && password.equals(userDetails.getPassword())) {
//                        Log.d("User Details", userDetails.getUsername());
//                        return true;
//                    }
//            }
//                Log.d("Value", String.valueOf(realm.where(Login.class).contains("username", username)));
//                return false;
//            }
        });

        signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
            }
        });

    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginBtn.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = usernameId.getText().toString();
        String password = passwordId.getText().toString();

        // TODO: Implement your own authentication logic here.

        RealmResults<Login> results = realm.where(Login.class).findAllAsync();
        for (Login login:results) {
            if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {
                Log.e(TAG, login.getUsername());
                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();


                SharedPreferences.Editor editor = loggedIn.edit();
                editor.putBoolean("loggedIn", true);
//                editor.putString("username", login.getUsername());
                editor.apply();

                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the LocationActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        loginBtn.setEnabled(true);
        loginbool = true;
//        SharedPreferences loggedIn = getSharedPreferences("logged_in", MODE_PRIVATE);
//        SharedPreferences.Editor editor = loggedIn.edit();
//        editor.putBoolean("loggedIn", loginbool);
//        editor.apply();

        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginBtn.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = usernameId.getText().toString();
        String password = passwordId.getText().toString();

        if (username.isEmpty() || username.length()<3) {
            usernameId.setError("Enter proper username");
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

        return valid;


    }

//    public void showData(){
//        RealmResults<Login> studentResults = realm.where(Login.class).findAll();
//
//        String op = "";
//        realm.beginTransaction();
//        for (Login guest:studentResults){
//            op+=guest.toString();
//        }
//
////        log.setText(op);
//    }
//
//    private void showSnackBar(String msg) {
//        try {
//            Snackbar.make(findViewById(R.id.content), msg, Snackbar.LENGTH_SHORT).show();
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }
}
