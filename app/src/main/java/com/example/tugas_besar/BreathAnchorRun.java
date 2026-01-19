package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer; // Jangan lupa import
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BreathAnchorRun extends AppCompatActivity {

    TextView tvTimer, tvInstruction;
    Button btnFinishSession;
    LinearLayout btnRestart, btnAddTime;
    ImageView btnVolume; // Variabel tombol volume

    MediaPlayer mediaPlayer;
    boolean isMuted = false;

    CountDownTimer timer;
    long timeLeftInMillis = 300000;
    boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_anchor_run);

        tvTimer = findViewById(R.id.tvTimer);
        tvInstruction = findViewById(R.id.tvInstruction);
        btnFinishSession = findViewById(R.id.btnFinishSession);
        btnRestart = findViewById(R.id.btnRestart);
        btnAddTime = findViewById(R.id.btnAddTime);
        btnVolume = findViewById(R.id.btnVolume);

        // --- 1. SETTING MUSIK ---
        mediaPlayer = MediaPlayer.create(this, R.raw.music_focus);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        // --- 2. LOGIKA VOLUME ---
        btnVolume.setOnClickListener(v -> {
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
        });

        startTimer();

        // Tombol Finish
        btnFinishSession.setOnClickListener(v -> {
            stopMusic(); // Matikan musik pas finish
            finish();
        });

        // Tombol Restart
        btnRestart.setOnClickListener(v -> {
            stopTimer();
            timeLeftInMillis = 300000;
            startTimer();
            Toast.makeText(this, "Timer Restarted", Toast.LENGTH_SHORT).show();
        });

        // Tombol + 5 Min
        btnAddTime.setOnClickListener(v -> {
            stopTimer();
            timeLeftInMillis += 300000;
            startTimer();
            Toast.makeText(this, "Added 5 minutes", Toast.LENGTH_SHORT).show();
        });
    }

    private void startTimer() {
        timer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();

                long seconds = (millisUntilFinished / 1000) % 12;

                if (seconds > 8) {
                    tvInstruction.setText("Inhale...");
                } else if (seconds > 4) {
                    tvInstruction.setText("Hold...");
                } else {
                    tvInstruction.setText("Exhale...");
                }
            }

            @Override
            public void onFinish() {
                tvTimer.setText("00:00");
                tvInstruction.setText("Session Complete");
                stopMusic(); // Musik berhenti kalau waktu habis
            }
        }.start();
        isRunning = true;
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
        isRunning = false;
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        tvTimer.setText(timeFormatted);
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
        stopMusic();
    }
}