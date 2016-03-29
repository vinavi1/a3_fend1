package com.example.akramkhan.complaint_trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class complaintdetails extends AppCompatActivity {
    private TextView cmpDid;
    private TextView cmpDtitle;
    private TextView cmpDcount;
    private TextView cmpDtime;
    private TextView cmpDhostel;
    private TextView cmpDstatus;
    private TextView cmpDtype;
    private TextView cmpDdesc;
    private TextView cmpDtechi;
    private Button Resolved;
    private Button upvote;
    private Button downvote;
    private Button Notify;
    private Button redundant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintdetails);
        Intent intent=getIntent();

        cmpDid= (TextView) findViewById(R.id.cmpDid);
        cmpDtitle= (TextView) findViewById(R.id.cmpDtitle);
        cmpDtime= (TextView) findViewById(R.id.cmpDtime);
        cmpDtype= (TextView) findViewById(R.id.cmpDtype);
        cmpDcount= (TextView) findViewById(R.id.cmpDcount);
        cmpDhostel= (TextView) findViewById(R.id.cmpDhostel);
        cmpDstatus= (TextView) findViewById(R.id.cmpDstatus);
        cmpDtechi = (TextView) findViewById(R.id.cmpDtechi);
        cmpDtype = (TextView) findViewById(R.id.cmpDtype);
        cmpDdesc= (TextView) findViewById(R.id.cmpDdesc);

        Resolved = (Button) findViewById(R.id.resolved);
        upvote = (Button) findViewById(R.id.upvote);
        downvote = (Button) findViewById(R.id.downvote);
        Notify = (Button) findViewById(R.id.notify);
        redundant = (Button) findViewById(R.id.redundant);



        String url="http://192.168.202.1:80/my_api/complaints/complaint?complaint_id="+intent.getStringExtra("complaintid");
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    int success = jsonObject.getInt("success");
                    if(success==1){
                        JSONObject jsonObject1 = jsonObject.getJSONObject("complaints");
                        cmpDid.setText(jsonObject1.getString("complaint_id"));
                        cmpDtitle.setText(jsonObject1.getString("title"));
                        cmpDtime.setText(jsonObject1.getString("time"));
                        cmpDcount.setText(jsonObject1.getString("upvote"));
                        cmpDhostel.setText(jsonObject1.getString("hostel"));
                        cmpDstatus.setText(jsonObject1.getString("complaint_status"));
                        cmpDdesc.setText(jsonObject1.getString("description"));
                        cmpDtype.setText(jsonObject1.getString("complaint_type"));
                        cmpDtechi.setText(jsonObject1.getString("tech_id"));
                    }
                    else {
                        Toast.makeText(complaintdetails.this, "unable to get complaint details", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(),"volley error",Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        String userid = intent.getStringExtra("userid");
        switch (userid){
            case "0":
                if(!cmpDtype.getText().toString().matches("0")){
                    Resolved.setVisibility(View.GONE);
                }
                else{
                    upvote.setVisibility(View.GONE);
                    downvote.setVisibility(View.GONE);
                }
                redundant.setVisibility(View.GONE);
                Notify.setVisibility(View.GONE);
                break;
            case "1":
                upvote.setVisibility(View.GONE);
                downvote.setVisibility(View.GONE);
                redundant.setVisibility(View.GONE);
                break;
            case "2":
                Notify.setVisibility(View.GONE);
                upvote.setVisibility(View.GONE);
                downvote.setVisibility(View.GONE);
                Resolved.setVisibility(View.GONE);
                break;
        }

        Resolved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Resolved", Toast.LENGTH_SHORT).show();
            }
        });

        upvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "upvoted", Toast.LENGTH_SHORT).show();
            }
        });

        downvote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "downvoted", Toast.LENGTH_SHORT).show();
            }
        });

        Notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),ComposeNotification.class);
                intent1.putExtra("cmpId",cmpDid.getText().toString());
                startActivity(intent1);
            }
        });

        redundant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "marked as redundant", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
