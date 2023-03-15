package com.example.sharedreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText usermessage;
    Button counter;
    CheckBox remember;
    int count=0;
    String name;
    String message;

    SharedPreferences sharedPreferences;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username= findViewById(R.id.editTextTextPersonName);
        usermessage=findViewById(R.id.editTextTextMultiLine);
        counter=findViewById(R.id.button);
        remember=findViewById(R.id.checkBox);

        counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count = count+1;
                counter.setText(""+count);
            }
        });
        retriveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    public  void  saveData(){
        sharedPreferences=getSharedPreferences("saveData",MODE_PRIVATE);
        name=username.getText().toString();
        message=usermessage.getText().toString();
        if (remember.isChecked())
        {
            isChecked=true;
        }
        else
        {
            isChecked=false;
        }
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("key name",name);
        editor.putString("key message",message);
        editor.putInt("key count",count);
        editor.putBoolean("key remember",isChecked);
        editor.commit();
        Toast.makeText(getApplicationContext(), "Your data is saved", Toast.LENGTH_SHORT).show();
    }
    public void retriveData(){
        sharedPreferences=getSharedPreferences("saveData",MODE_PRIVATE);
        name=sharedPreferences.getString("key name",null);
        message=sharedPreferences.getString("key message",null);
        count=sharedPreferences.getInt("key count",0);
        isChecked=sharedPreferences.getBoolean("key remember",false);
        username.setText(name);
        usermessage.setText(message);
        counter.setText(""+count);

        if(isChecked)
        {
            remember.setChecked(true);
        }
        else {
            remember.setChecked(false);
        }

    }
}