package com.pranalspk.saarthi;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class ProfileActivity extends AppCompatActivity {
    private Button sosBtn;
    private Button docBtn;
    private Button pharmaBtn;
    private Button pulseBtn;
    TextToSpeech t1;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sosBtn = findViewById(R.id.sosBtn);
        docBtn = findViewById(R.id.docBtn);
        pharmaBtn = findViewById(R.id.pharmaBtn);
        pulseBtn = findViewById(R.id.pulseBtn);
        db=new DBHelper(this);


        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });
    }

    public void sosBtnClick(View view) {
        t1.speak("CALLING NOW", TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(getApplicationContext(), "Calling your emergency contact ", Toast.LENGTH_LONG).show();

        String uname=getIntent().getStringExtra("USERNAME");
        String sosno= db.get_sosno(uname);
       //String sosno="9998219595";


        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+sosno));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
        Toast.makeText(getApplicationContext(), "Calling your emergency contact "+sosno, Toast.LENGTH_LONG).show();
    }




    public void docBtnClick(View view)
    {
        t1.speak("LETS GET CONNECTED TO YOUR DOCTOR", TextToSpeech.QUEUE_FLUSH, null);
        //t1.speak("Welcome home "+username, TextToSpeech.QUEUE_FLUSH, )
        Toast.makeText(getApplicationContext(),"CLICKED ON doctor ",Toast.LENGTH_LONG).show();
    }

    public void pharmaBtnClick(View view)
    {

        Toast.makeText(getApplicationContext(),"CLICKED ON PHARMACIST ",Toast.LENGTH_LONG).show();

        Intent i = new Intent(ProfileActivity.this,PharmacistActivity.class);
        startActivity(i);
        t1.speak("JUST TYPE IN THE MEDICINES YOU NEED AND I WILL GET THEM FOR YOU", TextToSpeech.QUEUE_FLUSH, null);

    }

    public void pulseBtnClick(View view)
    {
        t1.speak("FITNESS 24 7", TextToSpeech.QUEUE_FLUSH, null);
        //t1.speak("Welcome home "+username, TextToSpeech.QUEUE_FLUSH, )
        Toast.makeText(getApplicationContext(),"CLICKED ON PULSE MAINTAINER ",Toast.LENGTH_LONG).show();
    }

    public void logoutBtnClick(View view)
    {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username" ,"empty");
        editor.putString("password","empty");
        editor.commit();
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        t1.speak("LOGGING OUT FROM THE APP", TextToSpeech.QUEUE_FLUSH, null);
        //t1.speak("Welcome home "+username, TextToSpeech.QUEUE_FLUSH, )
        Toast.makeText(getApplicationContext(),"LOGGING OUT... ",Toast.LENGTH_LONG).show();
        this.finish();
        startActivity(intent);

    }


}
