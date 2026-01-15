package com.example.totalrelease;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TimerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // 1. Inisialisasi Tombol
        Button btnFinish = findViewById(R.id.btnFinish);
        LinearLayout btnAddTime = findViewById(R.id.btnAddTime);
        LinearLayout btnRestart = findViewById(R.id.btnRestart);

        // 2. Logika Tombol Finish
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menutup halaman timer dan kembali ke halaman sebelumnya
                finish();

                // ATAU jika ingin kembali langsung ke Menu Utama (MainActivity):
                // Intent intent = new Intent(TimerActivity.this, MainActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                // startActivity(intent);
                // finish();
            }
        });

        // 3. Logika Tambah Waktu (+ 5 min)
        btnAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disini logika menambah timer nanti
                Toast.makeText(TimerActivity.this, "Waktu ditambah 5 menit", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. Logika Restart
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Disini logika mengulang timer
                Toast.makeText(TimerActivity.this, "Timer di-restart", Toast.LENGTH_SHORT).show();
            }
        });
    }
}