package com.example.ratemypritch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LandingPage extends AppCompatActivity {

 //   DB_Helper DB;
    TextView txtString, txtString2;
    public String url= "https://quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com/quote?token=ipworld.info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button loglog, signUp, signUpLater;
        String uname, pass;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        txtString= (TextView)findViewById(R.id.txtRequest);
        txtString2= (TextView)findViewById(R.id.textView16);

        try {
            run();
            run2();
        } catch (IOException e) {
            e.printStackTrace();
        }

        EditText email_signin = (EditText) findViewById(R.id.email);
        EditText password_signin = (EditText) findViewById(R.id.pass);

        loglog = (Button) findViewById(R.id.button6);
        signUp = (Button) findViewById(R.id.button7);
        signUpLater = (Button) findViewById(R.id.button8);
        //DB = new DB_Helper(this);
        EditText username = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.pass);


        Intent intentGoMain = new Intent(this, MainView.class);
        loglog.setOnClickListener(new View.OnClickListener() {
            Boolean loggedin = false;

            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View view) {
//                loggedin = true;
//                startActivity(intentGoMain);

                String user = email_signin.getText().toString();
                String pass = password_signin.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LandingPage.this, "Enter all the fields.", Toast.LENGTH_SHORT).show();
                //else {
                    //Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                   // if (checkuserpass == true) {
                        Toast.makeText(LandingPage.this, "Sign in successful!", Toast.LENGTH_SHORT).show();
                        startActivity(intentGoMain);
                  //  } else {
                   //     Toast.makeText(LandingPage.this, "Invalid Account.", Toast.LENGTH_SHORT).show();
                  //  }
              //  }
            }
        });

        Intent intentSignUp = new Intent(this, sign_up_page.class);
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intentSignUp);
            }
        });



        Intent intentUnsigned = new Intent(this, MainView.class);
        signUpLater.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(intentUnsigned);
            }
        });

    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();



        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("X-RapidAPI-Key", "fb352cc763msh83751fccbd4f286p102897jsn2591a4b47bf5")
                .addHeader("X-RapidAPI-Host", "quotes-inspirational-quotes-motivational-quotes.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                LandingPage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] bro = myResponse.split(":");
                        txtString.setText(bro[2].substring(0,bro[2].length()-1));
                    }
                });

            }
        });
    }

    void run2() throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://covid-193.p.rapidapi.com/statistics?country=Canada")
                .get()
                .addHeader("X-RapidAPI-Key", "fb352cc763msh83751fccbd4f286p102897jsn2591a4b47bf5")
                .addHeader("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                LandingPage.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] bros = myResponse.split(":");
                        String[] apple = bros[13].split(",");
                        int cases = Integer.parseInt(apple[0]);
                        if(cases<100)
                            txtString2.setText("It's safe to go to Pritchard!");
                        else
                            txtString2.setText("Be careful and mask up!");
                    }
                });

            }
        });
    }

}