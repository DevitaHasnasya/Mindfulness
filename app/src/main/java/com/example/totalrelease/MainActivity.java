package com.example.totalrelease;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Variabel status audio
    private boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 1. WAJIB PALING ATAS
        setContentView(R.layout.activity_main); // 2. PASANG LAYOUT DULU

        // === BARU SETELAH ITU KITA CARI ID-NYA ===

        // --- BAGIAN 1: PINDAH HALAMAN (KARTU PINK) ---
        View cardInstantRelief = findViewById(R.id.cardInstantRelief);

        // Cek agar tidak null (safety check)
        if (cardInstantRelief != null) {
            cardInstantRelief.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, InstantReliefActivity.class);
                    startActivity(intent);
                }
            });
        }

        // --- BAGIAN 2: DEEP CALM (KARTU BIRU) ---
        View cardDeepCalm = findViewById(R.id.cardDeepCalm);

        if (cardDeepCalm != null) {
            cardDeepCalm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, DeepCalmActivity.class);
                    startActivity(intent);
                }
            });
        }

        // --- BAGIAN 3: LOGIKA AUDIO ---
        ImageView btnAudio = findViewById(R.id.btnAudio);

        if (btnAudio != null) {
            btnAudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isMuted) {
                        isMuted = false;
                        btnAudio.setImageResource(R.drawable.low);
                        Toast.makeText(MainActivity.this, "Audio ON", Toast.LENGTH_SHORT).show();
                    } else {
                        isMuted = true;
                        btnAudio.setImageResource(R.drawable.mute);
                        Toast.makeText(MainActivity.this, "Audio MUTED", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}