package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import android.view.View;

public class Yoga extends AppCompatActivity {

    VideoView videoViewYoga;
    TextView tvPoseName;
    ImageView btnClose;

    // Tombol-tombol pose 1 sampai 8
    Button btnPose1, btnPose2, btnPose3, btnPose4, btnPose5, btnPose6, btnPose7, btnPose8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga);

        // --- 1. INISIALISASI KOMPONEN ---
        videoViewYoga = findViewById(R.id.videoViewYoga);
        tvPoseName = findViewById(R.id.tvPoseName);
        btnClose = findViewById(R.id.btnClose);

        btnPose1 = findViewById(R.id.btnPose1);
        btnPose2 = findViewById(R.id.btnPose2);
        btnPose3 = findViewById(R.id.btnPose3);
        btnPose4 = findViewById(R.id.btnPose4);
        btnPose5 = findViewById(R.id.btnPose5);
        btnPose6 = findViewById(R.id.btnPose6);
        btnPose7 = findViewById(R.id.btnPose7);
        btnPose8 = findViewById(R.id.btnPose8);

        // --- 2. SETTING DEFAULT (Putar Video 1 saat dibuka) ---
        // Pastikan nama file di folder raw adalah: yoga_01.mp4
        playYogaVideo(R.raw.yoga01, "Pose 1: Warming Up");
        // Set warna tombol 1 jadi aktif (Biru)
        resetButtonColors();
        btnPose1.setBackgroundColor(0xFF005C88);

        // --- 3. LOGIKA KLIK TOMBOL (1 - 8) ---

        btnPose1.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga01, "Pose 1: Warming Up");
            resetButtonColors();
            btnPose1.setBackgroundColor(0xFF005C88);
        });

        btnPose2.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga02, "Pose 2: Neck Stretch");
            resetButtonColors();
            btnPose2.setBackgroundColor(0xFF005C88);
        });

        btnPose3.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga03, "Pose 3: Cobra Pose");
            resetButtonColors();
            btnPose3.setBackgroundColor(0xFF005C88);
        });

        btnPose4.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga04, "Pose 4: Tree Pose");
            resetButtonColors();
            btnPose4.setBackgroundColor(0xFF005C88);
        });

        btnPose5.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga05, "Pose 5: Warrior Pose");
            resetButtonColors();
            btnPose5.setBackgroundColor(0xFF005C88);
        });

        // --- TAMBAHAN BARU UNTUK POSE 6, 7, 8 ---

        btnPose6.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga06, "Pose 6: Child Pose");
            resetButtonColors();
            btnPose6.setBackgroundColor(0xFF005C88);
        });

        btnPose7.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga07, "Pose 7: Plank");
            resetButtonColors();
            btnPose7.setBackgroundColor(0xFF005C88);
        });

        btnPose8.setOnClickListener(v -> {
            playYogaVideo(R.raw.yoga08, "Pose 8: Deep Relax");
            resetButtonColors();
            btnPose8.setBackgroundColor(0xFF005C88);
        });

        // --- 4. AGAR VIDEO LOOPING (Ngulang Terus) ---
        videoViewYoga.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        // Tombol Keluar
        btnClose.setOnClickListener(v -> finish());
    }

    // --- FUNGSI PINTAR GANTI VIDEO ---
    private void playYogaVideo(int videoResId, String title) {
        // Ganti Judul
        tvPoseName.setText(title);

        // Ganti Source Video
        String videoPath = "android.resource://" + getPackageName() + "/" + videoResId;
        Uri uri = Uri.parse(videoPath);
        videoViewYoga.setVideoURI(uri);

        // Mulai
        videoViewYoga.start();
    }

    // --- FUNGSI RESET WARNA SEMUA TOMBOL ---
    private void resetButtonColors() {
        int inactiveColor = 0xFF90A4AE; // Abu-abu

        btnPose1.setBackgroundColor(inactiveColor);
        btnPose2.setBackgroundColor(inactiveColor);
        btnPose3.setBackgroundColor(inactiveColor);
        btnPose4.setBackgroundColor(inactiveColor);
        btnPose5.setBackgroundColor(inactiveColor);
        btnPose6.setBackgroundColor(inactiveColor); // Tambahan
        btnPose7.setBackgroundColor(inactiveColor); // Tambahan
        btnPose8.setBackgroundColor(inactiveColor); // Tambahan
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoViewYoga != null) {
            videoViewYoga.stopPlayback();
        }
    }
}