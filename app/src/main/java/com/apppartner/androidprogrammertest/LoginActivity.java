package com.apppartner.androidprogrammertest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.apppartner.androidprogrammertest.httputils.HttpManager;
import com.apppartner.androidprogrammertest.httputils.RequestPackage;

import org.json.JSONException;
import org.json.JSONObject;

public class  LoginActivity extends ActionBarActivity
{
    public static final String TAG = LoginActivity.class.getSimpleName();

    private EditText txtUserName, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_login);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtUserName = (EditText) findViewById(R.id.txtUserName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onbtnLoginClicked(View view) {

        if((txtUserName.getText() != null) && (txtPassword.getText() != null)){
            String userName = txtUserName.getText().toString();
            String password = txtPassword.getText().toString();
            requestData(userName, password);
        } else {
            showAlertDialog("Error", "Please Type Username and Password!");
        }

    }

    private void showAlertDialog(String code, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(code);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.dialogPositiveString, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void requestData(String username, String password) {
        RequestPackage requestPackage = new RequestPackage();
        requestPackage.setMethod("POST");
        requestPackage.setUri("http://dev.apppartner.com/AppPartnerProgrammerTest/scripts/login.php");
        requestPackage.setParams("username", username);
        requestPackage.setParams("password", password);

        LoginAuthenticator loginAuthenticator = new LoginAuthenticator();
        loginAuthenticator.execute(requestPackage);

    }

    private class LoginAuthenticator extends AsyncTask<RequestPackage, Void, Void> {

        String code, message;
        long timeTaken;

        @Override
        protected Void doInBackground(RequestPackage... params) {
            long startTime = System.currentTimeMillis();
            try {

                String json = HttpManager.getData(params[0]);

                JSONObject jsonObject = new JSONObject(json);
                code = jsonObject.getString("code");
                message = jsonObject.getString("message");

            } catch (JSONException e) {
                Log.e(TAG, "JSONException" +e);
            }

            long finishTime = System.currentTimeMillis();
            timeTaken = finishTime - startTime;

            message += "\n" + "API call time = " + timeTaken + " millisecs" ;

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            showAlertDialog(code, message);
        }
    }
}
