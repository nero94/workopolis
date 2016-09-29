package com.edvantis.workopolis.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edvantis.workopolis.model.User;
import com.edvantis.workopolis.api.ApiInterface;
import com.edvantis.workopolis.workopolis.R;
import com.edvantis.workopolis.api.ServiceGenerator;

import org.apache.http.HttpStatus;

import java.io.IOException;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    public static final String PREFS_NAME = "UserData";


    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.btn_skip) Button skip;
    @InjectView(R.id.link_signup) TextView _signupLink;

    private String userName = "";
    private String email = "";
    private boolean authenticated = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        SharedPreferences userData = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        email = userData.getString("email", email);
        authenticated = userData.getBoolean("authenticated", authenticated);
        userName = userData.getString("userName", userName);

        if (authenticated == false) {
            setContentView(R.layout.activity_login);
            ButterKnife.inject(this);

            _loginButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        login();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            _signupLink.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(intent);
                }
            });

            skip.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                }
            });
            _signupLink.setVisibility(View.GONE);
        }
        if (authenticated == true) {
            setContentView(R.layout.activity_logout);

            TextView _logoutButton = (TextView) findViewById(R.id.viewLogoutButton);
            Button _vacancySearch = (Button) findViewById(R.id.viewVacancySeach);
            TextView userEmail = (TextView) findViewById(R.id.viewUserEmail);
            TextView _userName = (TextView) findViewById(R.id.viewUserName);

            _userName.setText(userName);
            userEmail.setText(email);

            _logoutButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    try {
                        SharedPreferences userData = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = userData.edit();
                        editor.putString("userName", "");
                        editor.putString("email", "");
                        editor.putBoolean("authenticated", false);
                        editor.commit();

                        ApiInterface loginService = ServiceGenerator.createService(ApiInterface.class);
                        Call<HttpStatus> call = loginService.basicLogout();
                        recreate();
                    }
                    catch (Exception e){
                        Log.v("Logout error: ", e.toString());
                    }
                }
            });

            _vacancySearch.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

    public void login() throws IOException {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        try {
            ApiInterface loginService = ServiceGenerator.createService(ApiInterface.class, email, password);
            Call<User> call = loginService.basicLogin();
            User user = call.execute().body();

            SharedPreferences userData = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = userData.edit();
            editor.putString("email", user.getEmail());
            editor.putBoolean("authenticated", true);
            editor.putString("userName", user.getName() + " " + user.getLastname());
            editor.commit();
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginSuccess();
                            progressDialog.dismiss();
                        }
                    }, 2000);
        }
        catch (Exception e){
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 2000);
            Log.v("Login error: ", e.toString());
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.setContentView(R.layout.activity_login);
                //
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.login_page) {
            Intent intentLogin = new Intent(this, LoginActivity.class);
            this.startActivity(intentLogin);
        }else
        if (id == R.id.activity_search) {
            Intent intentSearch = new Intent(this, SearchActivity.class);
            this.startActivity(intentSearch);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit application")
                .setMessage("Are you sure you want to exit application?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Toast.makeText(getBaseContext(), "Login success", Toast.LENGTH_LONG).show();
        recreate();
        //finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}