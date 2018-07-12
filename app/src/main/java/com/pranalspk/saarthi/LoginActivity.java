package com.pranalspk.saarthi;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private Button LoginButton;
    private EditText UsernameInput,PasswordInput;
    private Intent i;
    private String username,password;
    DBHelper dbHelper;
    TextToSpeech t1;
    CheckBox keepMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton = findViewById(R.id.LogInButton);
        UsernameInput = findViewById(R.id.UsernameInput);
        PasswordInput = findViewById(R.id.PasswordInput);
        keepMeCheckBox = findViewById(R.id.keepMeCheckBox);
        dbHelper = new DBHelper(this);
        //  TextView displayLabel = findViewById(R.id.displayLabel);
        // displayLabel.setText(dbHelper.displayAll());
        t1 = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    t1.setLanguage(Locale.US);
                }
            }
        });
        //t1.speak("HELLO I AM YOUR PERSONAL ASSISTANT SARTHI. IF YOU ARE A NEW USER, DONOT PANIC I WILL GUIDE YOU THROUGH THE APP REGISTRATION.JUST CLICK ON THE SIGN UP BUTTON.",TextToSpeech.QUEUE_FLUSH,null);
        checkPreferences();
    }

    public void loginBtnClick(View view){
        username = UsernameInput.getText().toString().trim();
        password = PasswordInput.getText().toString().trim();
        authenticate();
    }

    public void authenticate(){
        // Returns the password for the entered username from the table
        String correctPass = dbHelper.searchPass(username);

        if(correctPass.equals(password)){
            i = new Intent(LoginActivity.this,ProfileActivity.class);
            i.putExtra("USERNAME" ,username );
            startActivity(i);
            t1.speak("Welcome "+username+". I am your personal assistant saarthi. Just click on the service you want and I will help you through it", TextToSpeech.QUEUE_FLUSH, null);
            //t1.speak("Welcome home "+username, TextToSpeech.QUEUE_FLUSH, )
            Toast.makeText(getApplicationContext(),"Hello "+username,Toast.LENGTH_LONG).show();
            if(keepMeCheckBox.isChecked()) {
                storePreferences();
            }
        } else{
            showInvalidDialog();
            t1.speak("Oops check your username or password again", TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void checkPreferences(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        String username = preferences.getString("username", "empty");
        if(!username.equals("empty")){
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Welcome "+username, Toast.LENGTH_LONG).show();
        }
    }

    public void storePreferences(){
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username",username);
        editor.putString("password",password);
        editor.commit();
    }

    public void signUp(View view){
        i = new Intent(LoginActivity.this, SignUpActivity.class);
        t1.speak("Welcome to saarthi. Just enter your name age and set an easy password you can remember. Click sign up to avail all my services", TextToSpeech.QUEUE_FLUSH, null);
        startActivity(i);
    }

    public void showInvalidDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Invalid username/password");
        alertDialogBuilder.setTitle("Oops!!");
        alertDialogBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                UsernameInput.setText("");
                PasswordInput.setText("");
                    /*Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);*/
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        Toast.makeText(getApplicationContext(),"Invalid username/password",Toast.LENGTH_LONG).show();
    }

}