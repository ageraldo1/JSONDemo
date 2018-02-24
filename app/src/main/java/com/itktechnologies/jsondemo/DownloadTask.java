package com.itktechnologies.jsondemo;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Alexandre on 2/24/2018.
 */

public class DownloadTask  extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        String result = "";

        URL url = null;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();

            InputStreamReader reader = new InputStreamReader(in);

            int data = reader.read();

            while ( data != -1) {
                char current =(char) data;
                result += current;

                data = reader.read();
            }

        } catch (MalformedURLException ue) {
            ue.printStackTrace();
            return "MalformedURLException exception";

        } catch ( IOException ie) {
            ie.printStackTrace();
            return "IOException exception";
        }

        return result;


    }

    @Override
    protected void onPostExecute(String result) {
        JSONObject jsonObject = null;
        JSONArray jsonArray = null;
        String weatherInfo = null;

        super.onPostExecute(result);

        try {
            jsonObject = new JSONObject(result);
            weatherInfo = jsonObject.getString("weather");
            jsonArray = new JSONArray(weatherInfo);

            for ( int i = 0; i < jsonArray.length(); i++ ) {
                JSONObject jsonPart = jsonArray.getJSONObject(i);

                Log.i ("Main", jsonPart.getString("main"));
                Log.i ("Description", jsonPart.getString("description"));
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }
        Log.i("Weather Info Content", weatherInfo);
    }
}
