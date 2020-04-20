package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RunnableActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView result;
    Button btn;
    Button btnStart;
    Button btnPause;
    private Handler handler;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runnable);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        result = findViewById(R.id.result);
        btn = findViewById(R.id.btnClick);
        btnStart = findViewById(R.id.start);
        btnPause = findViewById(R.id.pause);
        handler = new Handler();
        final java.lang.Runnable runnable = new java.lang.Runnable() {
            @Override
            public void run() {
                count++;
                result.setText(String.valueOf(count));
                handler.postDelayed(this,1000);
            }
        };

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new LoginTask());
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.post(runnable);
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });

    }

    public class LoginTask implements java.lang.Runnable {
        private final String regex = "\\w{2}\\d\\w{2}\\d\\p{Upper}[!,@,#,$,%,&,\\*]\\d\\w{2}\\d\\w\\p{Upper}\\d[!,@,#,$,%,&,\\*]";
        private Pattern pat;
        private Matcher match;
        @Override
        public void run() {
            String passText = password.getText().toString();
            pat = Pattern.compile(regex);
            match = pat.matcher(passText);
            if(match.matches()){
                result.setText("success");
            }else{
                result.setText("error");
            }

        }

    }
}
