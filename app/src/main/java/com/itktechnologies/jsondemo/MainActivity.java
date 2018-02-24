package com.itktechnologies.jsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final String apiURL = "http://samples.openweathermap.org/data/2.5/weather?zip=30024,us&appid=b6907d289e10d714a6e88b30761fae22";
    //private final String apiURL = "http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownloadTask task = new DownloadTask();
        task.execute(apiURL);



    }
}
