package com.example.avaneesh.meetup;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    final public static String BASE_URL="http://18.221.8.243/meetup/getdata.php?";
    Request request;
    OkHttpClient client;
    String name;
    String date;
    String venue;
    String details;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;
    Button[] button;
    String responseBody;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        client = new OkHttpClient();
        request = new Request.Builder().url(BASE_URL).build();
        ll=(LinearLayout)findViewById(R.id.buttonlayout);
        //lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseBody=response.body().string();
                Log.i("RESPONSE" + ": getData", responseBody);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            JSONObject responsejson=new JSONObject(responseBody);
                            JSONArray events=null;
                            if(responsejson.getInt("success")==200)
                            {
                                Log.i("RESPONSE"+": getData",responsejson.getString("message"));
                                events=responsejson.getJSONArray("event");
                                //Log.d("SERVER RESPONSE"+": getData",")
                                button=new Button[events.length()];
                                for(int i=0;i<events.length();i++)
                                {
                                    JSONObject obj=events.getJSONObject(i);
                                    name=obj.getString("name");
                                    date=obj.getString("dat");
                                    venue=obj.getString("venue");
                                    details=obj.getString("details");

                                    button[i]=new Button(getApplicationContext());
                                    button[i].setText(name);
                                    button[i].setId(i);
                                    ll.addView(button[i]);

                                    Button btn = (Button) findViewById(button[i].getId());

                                    btn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                           // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
                                    intent.putExtra("name",name);
                                    intent.putExtra("date",date);
                                    intent.putExtra("venue",venue);
                                    intent.putExtra("details",details);
                                    startActivity(intent);
                                        }
                                    });

                                }
                            }
                        }
                        catch (JSONException e){}

                    }
                });

            }
        });


    }
}
