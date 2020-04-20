package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Second_Activity extends AppCompatActivity {

    private Button btnBoth;
    private TextView txt;
    private FragmentManager fm;
    private int finish2=0,finish1=0;
    private ExampleFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_);

        btnBoth = findViewById(R.id.button);
        txt = findViewById(R.id.textView);
        fm = getSupportFragmentManager();

        btnBoth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("");
                frag = ExampleFragment.newInstance();
                frag.show(fm,"fragment_example");
                new AsyncFirst().execute();
                new AsyncSecond().execute();
            }
        });
    }

    class AsyncFirst extends AsyncTask<Void,Void,Void>{
        private int seconds;
        private ExampleFragment frag;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            seconds = new Random().nextInt((5-3)+1)+3;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(seconds * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish1=1;
            checkFinishProceses();
        }
    }

    class AsyncSecond extends AsyncTask<Void,Void,Void>{
        private int seconds;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            seconds = new Random().nextInt((5-2)+1)+2;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(seconds*1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish2=1;
            checkFinishProceses();
        }
    }

    public void checkFinishProceses(){
        if(finish2==1 && finish1==1) {
            int result1 = new Random().nextInt(2);
            int result2 = new Random().nextInt(2);
            if(result1==1 && result2==1){
                txt.setText("successful");
            }else{
                txt.setText("error");
            }
            frag.dismiss();
        }
    }
}
