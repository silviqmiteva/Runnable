package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnStart,nextAct,runnableActivity;
    private EditText numberValue;
    private TextView status;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.button);
        numberValue = findViewById(R.id.number);
        status = findViewById(R.id.status);
        fm = getSupportFragmentManager();
        nextAct = findViewById(R.id.nextActivity);
        runnableActivity = findViewById(R.id.runnableActivity);

        nextAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Second_Activity.class);
                startActivity(i);
            }
        });

        runnableActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RunnableActivity.class);
                startActivity(i);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredValue = numberValue.getText().toString();
                if(enteredValue.length()>0) {
                    int number = Integer.parseInt(enteredValue);
                    new AcyncCaller().execute(number);

                } else{
                    Toast.makeText(getApplicationContext(),"Field can not be empty",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class AcyncCaller extends AsyncTask<Integer,Integer,Void>{
        private ExampleFragment frag;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           frag = ExampleFragment.newInstance();
           frag.show(fm,"fragment_example");
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            while(integers[0]>0){
                try {
                    publishProgress(integers[0]);
                   Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                integers[0]--;
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            frag.setCurrentNumber(values[0]);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            frag.dismiss();
            status.setVisibility(View.VISIBLE);
            status.setText("success");
            status.setTextColor(Color.GREEN);
        }
    }


}
