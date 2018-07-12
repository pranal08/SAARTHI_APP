package com.pranalspk.saarthi;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class PharmacistActivity extends AppCompatActivity {
    private EditText med1;
    private EditText amnt1;
    private EditText med2;
    private EditText amnt2;
    TextToSpeech t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacist);
        med1=findViewById(R.id.Med1Input);
        med2=findViewById(R.id.Med2Input);
        amnt1=findViewById(R.id.amnt1Input);
        amnt2=findViewById(R.id.amnt2Input);

        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.US);
                }
            }
        });

    }

    public void BuyBtnClick(View view)
    {
        String m1=med1.getText().toString();
        String m2=med2.getText().toString();
        String a1=amnt1.getText().toString();
        String a2=amnt2.getText().toString();
        String phoneNo="7600447665";



        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo, null, "LIST OF MEDICINES NEEDED:\n\n MEDICINE 1: "+m1+" "+a1+"\nMEDICINE 2: "+m2+" "+a2+"\n\nADDRESS: AS MENTIONED BY THE USER DURING REGISTRATION", null, null);

        t1.speak("your message has been sent. You will get your medicines shortly", TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(getApplicationContext(), "SMS has been sent.",
                Toast.LENGTH_LONG).show();

    }
}
