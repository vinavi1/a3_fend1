package com.example.akramkhan.complaint_trial;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Akram Khan on 27-03-2016.
 */
public class complaintslistadapter extends BaseAdapter {

    private Context context;
    private List<complaint> complaintlist;

    public complaintslistadapter(Context context,List<complaint> complaintlist){
        this.context=context;
        this.complaintlist=complaintlist;
    }

    @Override
    public int getCount() {
        return complaintlist.size();
    }

    @Override
    public Object getItem(int position) {
        return complaintlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.item_list,null);
        TextView votes = (TextView) v.findViewById(R.id.compvotes);
        TextView title = (TextView) v.findViewById(R.id.comptitle);
        TextView complaintid = (TextView) v.findViewById(R.id.complaintid);
        TextView time = (TextView) v.findViewById(R.id.times);

        title.setText(complaintlist.get(position).getComplaintTitle());
        complaintid.setText(complaintlist.get(position).getComplaintid());
        time.setText(complaintlist.get(position).getTime());
        votes.setText(""+complaintlist.get(position).getVotes());

        v.setTag(complaintlist.get(position).getComplaintid());

        return v;
    }
}
