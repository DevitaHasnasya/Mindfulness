package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer; // Import MediaPlayer
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class BreathAnchorStart extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView btnVolume, btnClose;
    Button btnStartAnchor;
    boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_anchor_start);

        // --- 1. SETTING MUSIK (Nyalakan Musik Lagi) ---
        mediaPlayer = MediaPlayer.create(this, R.raw.music_focus);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        // --- 2. LOGIKA TOMBOL VOLUME (Sama seperti Focus Flow) ---
        btnVolume = findViewById(R.id.btnVolume);
        btnVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        // Matikan (Mute)
                        mediaPlayer.pause();
                        btnVolume.setImageResource(android.R.drawable.ic_lock_silent_mode);
                        isMuted = true;
                    } else {
                        // Nyalakan (Unmute)
                        mediaPlayer.start();
                        btnVolume.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
                        isMuted = false;
                    }
                }
            }
        });

        // --- 3. TOMBOL CLOSE ---
        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            finish(); // Kembali ke Focus Flow
        });

        // --- 4. TOMBOL START SESSION ---
        btnStartAnchor = findViewById(R.id.btnStartAnchor);
        btnStartAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Matikan musik intro sebelum pindah ke timer
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                }

                // Pindah ke halaman Timer (Run)
                Intent intent = new Intent(BreathAnchorStart.this, BreathAnchorRun.class);
                startActivity(intent);
                finish(); // Tutup halaman intro ini
            }
        });
    }

    // --- 5. CEGAH MUSIK BOCOR ---
    @Override
    protected void onResume() {
        super.onResume();
        if (mediaPlayer != null && !isMuted && !mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}