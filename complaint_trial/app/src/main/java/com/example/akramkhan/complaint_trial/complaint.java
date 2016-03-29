package com.example.akramkhan.complaint_trial;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by Akram Khan on 27-03-2016.
 */
public class complaint{
    private int id;
    private String complaintid;
    private String complaintTitle;
    private int votes;

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public complaint(int id,String complaintid,String complaintTitle,String time,int votes){
        this.id=id;
        this.votes = votes;
        this.complaintid=complaintid;
        this.complaintTitle=complaintTitle;
        this.time=time;
    }

    public String getComplaintid() {
        return complaintid;
    }

    public void setComplaintid(String complaintid) {
        this.complaintid = complaintid;
    }

    public String getComplaintTitle() {
        return complaintTitle;
    }

    public void setComplaintTitle(String complaintTitle) {
        this.complaintTitle = complaintTitle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

class VotesComparator implements Comparator<complaint>{
    @Override
    public int compare(complaint lhs, complaint rhs) {
        return rhs.getVotes()-lhs.getVotes();
    }
}

class TimeComparator implements Comparator<complaint>{
    @Override
    public int compare(complaint lhs, complaint rhs) {
        return lhs.getTime().compareToIgnoreCase(rhs.getTime());
    }
}


