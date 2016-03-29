package com.example.akramkhan.complaint_trial;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ComposeComplaint extends AppCompatActivity {
    private EditText cmpCtitle;
    private EditText cmpCdesc;
    private Button cmpsubmit;
    private Button takephoto;
    private RadioGroup type;
    private ImageView mImageView;
    private Spinner Hostel;
    private Spinner auth;

    String techi;
    String complaintType;

    private String[] hostels;
    private String[] technicians;
    private Uri fileUri;
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    public static final int MEDIA_TYPE_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose_complaint);

        cmpCdesc = (EditText) findViewById(R.id.cmpCdesc);
        cmpCtitle = (EditText) findViewById(R.id.cmpCtitle);
        cmpsubmit = (Button) findViewById(R.id.cmpsubmit);
        takephoto = (Button) findViewById(R.id.takephoto);
        type = (RadioGroup) findViewById(R.id.cmpCtype);
        Hostel = (Spinner) findViewById(R.id.cmpChostel);
        mImageView = (ImageView) findViewById(R.id.cmpCImage);
        auth = (Spinner) findViewById(R.id.technician);

        switch (auth.getSelectedItem().toString()){
            case "Electrician":
                techi="tech_1";
                break;
            case "Plumber":
                techi="tech_2";
                break;
            case "Sanitation":
                techi="tech_3";
                break;
            case "LAN":
                techi="tech_4";
                break;
            case "Carpenter":
                techi="tech_5";
                break;
        }

        String complaintTypes = ((RadioButton) findViewById(type.getCheckedRadioButtonId())).getText().toString();
        switch (complaintTypes){
            case "Personal":
                complaintType = "0";
                break;
            case "Hostel":
                complaintType = "1";
                break;
            case "Institute":
                complaintType = "2";
                break;
        }

        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureImage();
            }
        });

        cmpsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://192.168.201.1:80/my_api/complaints/ccomplaint?description="+cmpCdesc.getText().toString()
                        +"&title"+cmpCtitle.getText().toString()+"&complaint_type"+complaintType+"&tech_id"+techi+"&image=null";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if(jsonObject.getInt("success")==1){
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(ComposeComplaint.this,"complaint not posted try again", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(ComposeComplaint.this,"volley error compose", Toast.LENGTH_SHORT).show();
                    }
                });
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }
        });

        hostels = new String[]{"Karakoram","Nilgirir","Aravali","Jwalamukhi","Kumaon","Vindyachal","Shivalik","Zanskar","Satpura","Udaigiri","Girnar","Himadri","Kailash"};
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>
                (getApplicationContext(),R.layout.spinner_item,hostels);
        Hostel.setAdapter(arrayAdapter);

        technicians = new String[]{"Electrician","Plumber","Sanitation","LAN","Carpenter"};
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.spinner_item,technicians);
        auth.setAdapter(arrayAdapter1);
    }

    public void captureImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // start the image capture Intent
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                setPic();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void setPic() {
        try {
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);

            mImageView.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }


}
