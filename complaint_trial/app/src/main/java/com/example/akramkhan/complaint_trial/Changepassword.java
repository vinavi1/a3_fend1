package com.example.akramkhan.complaint_trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Changepassword extends AppCompatActivity {
    private EditText oldpassword;
    private EditText newpassword;
    private EditText Confirmpassword;
    private Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        confirm = (Button) findViewById(R.id.confirm);
        oldpassword = (EditText) findViewById(R.id.opassword);
        newpassword = (EditText) findViewById(R.id.npassword);
        Confirmpassword = (EditText) findViewById(R.id.cpassword);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                Toast.makeText(getApplicationContext(), "password changed successfully", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
