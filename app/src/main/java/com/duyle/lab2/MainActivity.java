package com.duyle.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRequest = findViewById(R.id.btn_request);
        TextView tvKetqua = findViewById(R.id.tv_ketqua);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<String, Void, String> asyncTask = new AsyncTask<String, Void, String>() {
                    @Override
                    protected String doInBackground(String... params) {

                        try {
                            URL url = new URL(params[0]);

                            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                            String urlString = "";
                            String current;
                            while ((current = in.readLine()) != null) {
                                urlString += current;
                            }

                            return  urlString;
                            //tvKetqua.setText(urlString);
                        } catch (MalformedURLException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

                    @Override
                    protected void onPostExecute(String s) {
                        super.onPostExecute(s);

                        tvKetqua.setText(s);
                    }
                };

                asyncTask.execute("http://10.82.0.113/api_android/nhanpost.php?name=Duy&diemTB=9.5");
            }
        });


    }
}