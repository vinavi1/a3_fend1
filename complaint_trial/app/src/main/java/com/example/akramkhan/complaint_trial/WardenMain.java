package com.example.akramkhan.complaint_trial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WardenMain extends AppCompatActivity {
    private ListView listView;
    private List<complaint> listofcomp;
    private complaintslistadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warden_main);

        Intent intent=getIntent();
        final String userid = intent.getStringExtra("userid");

        int type = intent.getIntExtra("sortType",1);
        listView = (ListView) findViewById(R.id.wcomplaintview);

        listofcomp = new ArrayList<>();
        listofcomp.add(new complaint(1,"COMPLAINT ID","COMPLAINT TITLE","14:20:00",43));
        listofcomp.add(new complaint(2, "complaint can be much longer than this if it is a string", "This is the complaint title for this complaint", "14:22:00",54));
        listofcomp.add(new complaint(3, "COMPLAINT ID", "COMPLAINT TITLE", "14:20:00",67));

        switch (type){
            case 1:
                Collections.sort(listofcomp,new TimeComparator());
                break;
            case 2:
                Collections.sort(listofcomp,new VotesComparator());
                break;
        }

        adapter=new complaintslistadapter(getApplicationContext(),listofcomp);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),complaintdetails.class);
                intent.putExtra("complaintid",""+view.getTag());
                intent.putExtra("userid",userid);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "" + view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                Intent intent = new Intent(getApplicationContext(),Changepassword.class);
                startActivity(intent);
                return true;
            case R.id.timesort:
                Intent intent1 = new Intent(getApplicationContext(),WardenMain.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent1.putExtra("sortType",1);
                startActivity(intent1);
                return true;
            case R.id.trending:
                Intent intent2 = new Intent(getApplicationContext(),WardenMain.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent2.putExtra("sortType",2);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
