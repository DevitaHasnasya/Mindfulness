package com.example.totalrelease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TimerDeepCalmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // PENTING: Hubungkan dengan layout XML yang baru dibuat
        setContentView(R.layout.activity_timer_deep_calm);

        Button btnFinish = findViewById(R.id.btnFinish);
        LinearLayout btnAddTime = findViewById(R.id.btnAddTime);
        LinearLayout btnRestart = findViewById(R.id.btnRestart);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimerDeepCalmActivity.this, "Waktu ditambah 5 menit", Toast.LENGTH_SHORT).show();
            }
        });

        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TimerDeepCalmActivity.this, "Timer di-restart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}