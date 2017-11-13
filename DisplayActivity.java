package com.example.avaneesh.meetup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayActivity extends AppCompatActivity {
Button b=(Button)findViewById(R.id.register);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String date = intent.getStringExtra("date");
        String venue = intent.getStringExtra("venue");
        String details = intent.getStringExtra("details");
        TextView t1=(TextView)findViewById(R.id.nam);
        TextView t2=(TextView)findViewById(R.id.dat);
        TextView t3=(TextView)findViewById(R.id.venu);
        TextView t4=(TextView)findViewById(R.id.detail);
        t1.setText(name);
        t2.setText(date);
        t3.setText(venue);
        t4.setText(details);        //Toast.makeText(DisplayActivity.this, name, Toast.LENGTH_SHORT).show();

    b.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(DisplayActivity.this,RegisterActivity.class);
            startActivity(i);
        }
    });


    }

}
