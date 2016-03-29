package com.example.akramkhan.complaint_trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ComposeNotification extends AppCompatActivity {
    private TextView NotifiTitle;
    private TextView NotifiId;
    private TextView NotifiDesc;
    private Button postNotifi;
    private EditText NotifiDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_notification);

        Intent intent= getIntent();
        String id=intent.getStringExtra("cmpId");


        NotifiDesc = (TextView) findViewById(R.id.NotifiDesc);
        NotifiDetails = (EditText) findViewById(R.id.Notifidetails);
        NotifiId = (TextView) findViewById(R.id.NotifiId);
        postNotifi = (Button) findViewById(R.id.NotifiPost);
        NotifiTitle = (TextView) findViewById(R.id.NotifiTitle);

        NotifiId.setText(id);

        postNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Notification posted", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
