package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ShoutOut extends AppCompatActivity {

    ImageView btnClose;
    CardView cardRain, cardBrown, cardCafe;
    ImageView iconPlayRain, iconPlayBrown, iconPlayCafe;

    MediaPlayer mediaPlayer;

    // Kita simpan ID lagu yang sedang main biar tau harus stop yang mana
    int currentPlayingId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shout_out);

        btnClose = findViewById(R.id.btnClose);

        // Kartu
        cardRain = findViewById(R.id.cardRain);
        cardBrown = findViewById(R.id.cardBrown);
        cardCafe = findViewById(R.id.cardCafe);

        // Ikon Play/Pause kecil di kanan kartu
        iconPlayRain = findViewById(R.id.iconPlayRain);
        iconPlayBrown = findViewById(R.id.iconPlayBrown);
        iconPlayCafe = findViewById(R.id.iconPlayCafe);

        // --- KLIK RAIN ---
        cardRain.setOnClickListener(v -> playSound(R.raw.sound_rain, 1));

        // --- KLIK BROWN NOISE ---
        cardBrown.setOnClickListener(v -> playSound(R.raw.sound_brown_noise, 2));

        // --- KLIK CAFE ---
        cardCafe.setOnClickListener(v -> playSound(R.raw.sound_cafe, 3));

        // Close Button
        btnClose.setOnClickListener(v -> finish());
    }

    private void playSound(int soundResId, int soundId) {
        // 1. Kalau user klik suara yang SAMA, berarti mau PAUSE/STOP
        if (currentPlayingId == soundId) {
            stopPlayer();
            resetIcons();
            currentPlayingId = 0; // Tidak ada yang main
            return;
        }

        // 2. Kalau user klik suara BEDA, matikan yang lama dulu
        stopPlayer();
        resetIcons();

        // 3. Mainkan yang baru
        mediaPlayer = MediaPlayer.create(this, soundResId);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            currentPlayingId = soundId;

            // Ubah ikon jadi PAUSE dan background jadi agak gelap biar ketahuan aktif
            updateActiveIcon(soundId);
        }
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void resetIcons() {
        // Kembalikan semua ikon jadi tombol PLAY (Segitiga)
        iconPlayRain.setImageResource(android.R.drawable.ic_media_play);
        iconPlayBrown.setImageResource(android.R.drawable.ic_media_play);
        iconPlayCafe.setImageResource(android.R.drawable.ic_media_play);

        // Kembalikan warna background putih
        cardRain.setCardBackgroundColor(0xFFFFFFFF);
        cardBrown.setCardBackgroundColor(0xFFFFFFFF);
        cardCafe.setCardBackgroundColor(0xFFFFFFFF);
    }

    private void updateActiveIcon(int soundId) {
        // Ubah ikon yang aktif jadi PAUSE (Garis dua) dan warnai background
        if (soundId == 1) { // Rain
            iconPlayRain.setImageResource(android.R.drawable.ic_media_pause);
            cardRain.setCardBackgroundColor(0xFFE0F7FA); // Biru muda banget
        }
        else if (soundId == 2) { // Brown
            iconPlayBrown.setImageResource(android.R.drawable.ic_media_pause);
            cardBrown.setCardBackgroundColor(0xFFFFF3E0); // Coklat muda banget
        }
        else if (soundId == 3) { // Cafe
            iconPlayCafe.setImageResource(android.R.drawable.ic_media_pause);
            cardCafe.setCardBackgroundColor(0xFFFBE9E7); // Merah bata muda
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }
}