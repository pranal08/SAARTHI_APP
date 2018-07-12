package com.pranalspk.saarthi;

import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    DBHelper dbHelper;
    //private EditText NameInput;
    private EditText UsernameInput;
    private EditText PasswordInput;
    private EditText AgeInput;
    private EditText SosNoInput;
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
       // NameInput = findViewById(R.id.NameInput);
        PasswordInput = findViewById(R.id.PasswordInput);
        UsernameInput = findViewById(R.id.UsernameInput);
        AgeInput = findViewById(R.id.AgeInput);
        SosNoInput = findViewById(R.id.SosNoInput);
        dbHelper = new DBHelper(this);
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.US);
                }
            }
        });

    }

    public void signUp(View view){
        User user = new User();
        String uname = UsernameInput.getText().toString();
        String pass = PasswordInput.getText().toString();
        boolean errorFree = checkErrors(uname, pass);
        /*if(studentIdInput.getText().toString() == null){

            return;
        }
        if(passwordInput.getText().toString() == null){

            return;
        }*/
        if(errorFree){
            user.setUsername(uname);
            user.setPass(pass);
            user.setAge(AgeInput.getText().toString());
            user.setSoscontact(SosNoInput.getText().toString());
            dbHelper.addUser(user);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Sign up complete");
            alertDialogBuilder.setTitle("Congratulations!");
            alertDialogBuilder.setPositiveButton("Login now", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

                    startActivity(intent);
                }
            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            t1.speak("Congratulations you have signed up successfully", TextToSpeech.QUEUE_FLUSH, null);
        } else {
            Toast.makeText(getApplicationContext(),"Sign up failed", Toast.LENGTH_LONG).show();
        }

    }

    public boolean checkErrors(String uname, String pass){
        if (uname.equals("")) {
            UsernameInput.setError("Student  ID can not be empty");
            return false;
        }
        if (pass.equals("")) {
            PasswordInput.setError("Password can not be empty");
            return false;
        }
        return true;
    }

}
