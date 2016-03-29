package com.example.akramkhan.complaint_trial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Akram Khan on 27-03-2016.
 */
public class complaintfragment extends Fragment{
    private ListView listView;
    private List<complaint> listofcomp;
    private complaintslistadapter adapter;
    private int position;
    private int sortType;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_view,null);


        listView = (ListView) v.findViewById(R.id.complaintview);

        position= (int) getArguments().get("section_number");
        sortType = (int) getArguments().get("sortType");
        switch (position){
            case 1:
                listofcomp = new ArrayList<>();
                String url = "http://192.168.201.1:80/my_api/complaints/hostel";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int success = jsonObject.getInt("success");
                            if(success==1){
                                JSONArray jsonArray = jsonObject.getJSONArray("complaints");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject j = jsonArray.getJSONObject(i);
                                    listofcomp.add(new complaint(i,j.getString("complaint_id"),j.getString("title"),j.getString("time"),Integer.parseInt(j.getString("upvote").toString())));
                                }
                            }
                            else {
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getContext(), "pppppp", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue= Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);
                break;
            case 2:
                listofcomp = new ArrayList<>();
                String url1 = "http://192.168.201.1:80/my_api/complaints/institute";
                StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Toast.makeText(getContext(),s, Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int success = jsonObject.getInt("success");
                            if(success==1){
                                JSONArray jsonArray = jsonObject.getJSONArray("complaints");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject j = jsonArray.getJSONObject(i);
                                    listofcomp.add(new complaint(i,j.getString("complaint_id"),j.getString("title"),j.getString("time"),Integer.parseInt(j.getString("upvote").toString())));
                                }
                            }
                            else {
                                Toast.makeText(getContext(),jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getContext(), "pppppp", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue1= Volley.newRequestQueue(getContext());
                requestQueue1.add(stringRequest1);
                break;
            case 3:
                listofcomp = new ArrayList<>();
                String url2 = "http://192.168.201.1:80/my_api/complaints/personal";
                StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int success = jsonObject.getInt("success");
                            if(success==1){
                                JSONArray jsonArray = jsonObject.getJSONArray("complaints");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject j = jsonArray.getJSONObject(i);
                                    listofcomp.add(new complaint(i,j.getString("complaint_id"),j.getString("title"),j.getString("time"),Integer.parseInt(j.getString("upvote").toString())));
                                }
                            }
                            else {
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getContext(), "pvolley error", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue2= Volley.newRequestQueue(getContext());
                requestQueue2.add(stringRequest2);
                break;
            case 4:
                listofcomp = new ArrayList<>();
                String url3 = "http://192.168.201.1:80/my_api/complaints/notifications";
                StringRequest stringRequest3 = new StringRequest(Request.Method.GET, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            int success = jsonObject.getInt("success");
                            if(success==1){
                                JSONArray jsonArray = jsonObject.getJSONArray("notifications");
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject j = jsonArray.getJSONObject(i);
                                    listofcomp.add(new complaint(i,j.getString("complaint_id"),j.getString("title"),j.getString("time"),Integer.parseInt(j.getString("upvote").toString())));
                                }
                            }
                            else {
                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getContext(), "pppppp", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue3= Volley.newRequestQueue(getContext());
                requestQueue3.add(stringRequest3);
                break;
        }
        if(sortType==1){
            Collections.sort(listofcomp,new TimeComparator());
        }
        else{
            Collections.sort(listofcomp,new VotesComparator());
        }
        adapter = new complaintslistadapter(getActivity(),listofcomp);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),complaintdetails.class);
                intent.putExtra("complaintid",""+view.getTag());
                intent.putExtra("userid","0");
                startActivity(intent);
                Toast.makeText(getContext(), ""+view.getTag(), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
