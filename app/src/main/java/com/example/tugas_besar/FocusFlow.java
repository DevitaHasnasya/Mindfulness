package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class FocusFlow extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView btnClose, btnVolume;
    CardView btnBreathAnchor, btnShoutOut; // Variabel untuk kedua kartu
    boolean isMuted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus_flow);

        // --- 1. SETTING MUSIK BACKGROUND ---
        mediaPlayer = MediaPlayer.create(this, R.raw.music_focus);

        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "File musik tidak ditemukan!", Toast.LENGTH_SHORT).show();
        }

        // --- 2. LOGIKA KARTU 1: BREATH ANCHOR (PINK) ---
        btnBreathAnchor = findViewById(R.id.btnBreathAnchor);
        btnBreathAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusicForActivity(); // Matikan musik menu
                Intent intent = new Intent(FocusFlow.this, BreathAnchorStart.class);
                startActivity(intent);
            }
        });

        btnShoutOut = findViewById(R.id.btnShoutOut);
        btnShoutOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusicForActivity(); 
                Intent intent = new Intent(FocusFlow.this, ShoutOut.class);
                startActivity(intent);
            }
        });

        btnVolume = findViewById(R.id.btnVolume);
        btnVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleMusic();
            }
        });

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> finish());
    }

    private void pauseMusicForActivity() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            btnVolume.setImageResource(android.R.drawable.ic_lock_silent_mode);
            isMuted = true;
        }
    }

    private void toggleMusic() {
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                btnVolume.setImageResource(android.R.drawable.ic_lock_silent_mode);
                isMuted = true;
            } else {
                mediaPlayer.start();
                btnVolume.setImageResource(android.R.drawable.ic_lock_silent_mode_off);
                isMuted = false;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Kalau kembali ke menu ini, nyalakan lagi musiknya (kecuali user emang nge-mute)
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