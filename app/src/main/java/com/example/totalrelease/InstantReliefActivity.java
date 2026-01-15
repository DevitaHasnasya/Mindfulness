package com.example.totalrelease;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class InstantReliefActivity extends AppCompatActivity {

    private boolean isMuted = false;

    // Variabel untuk menyimpan pilihan user (Default null/kosong)
    private String selectedCondition = null;
    private String selectedDuration = null;
    private String selectedSound = null;

    // List untuk mengelompokkan tombol agar mudah diatur warnanya
    private List<AppCompatButton> conditionButtons = new ArrayList<>();
    private List<AppCompatButton> durationButtons = new ArrayList<>();
    private List<AppCompatButton> soundButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instant_relief);

        // --- 1. SETUP TOMBOL HEADER (Close & Audio) ---
        setupHeaderButtons();

        // --- 2. INISIALISASI TOMBOL PILIHAN ---
        initButtons();

        // --- 3. LOGIKA TOMBOL START ---
        AppCompatButton btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // VALIDASI: Cek apakah semua sudah dipilih?
                if (selectedCondition == null) {
                    Toast.makeText(InstantReliefActivity.this, "Please choose a condition first", Toast.LENGTH_SHORT).show();
                } else if (selectedDuration == null) {
                    Toast.makeText(InstantReliefActivity.this, "Please choose duration", Toast.LENGTH_SHORT).show();
                } else if (selectedSound == null) {
                    Toast.makeText(InstantReliefActivity.this, "Please choose a sound", Toast.LENGTH_SHORT).show();
                } else {
                    // JIKA SEMUA LENGKAP -> PINDAH KE TIMER
                    Intent intent = new Intent(InstantReliefActivity.this, TimerActivity.class);

                    // (Opsional) Kirim data durasi ke halaman Timer agar timer-nya sesuai
                    intent.putExtra("DURATION_VALUE", selectedDuration);

                    startActivity(intent);
                }
            }
        });
    }

    private void setupHeaderButtons() {
        ImageView btnClosePage = findViewById(R.id.btnClosePage);
        btnClosePage.setOnClickListener(v -> finish());

        ImageView btnAudio = findViewById(R.id.btnAudioDetail);
        btnAudio.setOnClickListener(v -> {
            if (isMuted) {
                isMuted = false;
                btnAudio.setImageResource(R.drawable.low);
                Toast.makeText(InstantReliefActivity.this, "Audio ON", Toast.LENGTH_SHORT).show();
            } else {
                isMuted = true;
                btnAudio.setImageResource(R.drawable.mute);
                Toast.makeText(InstantReliefActivity.this, "Audio MUTED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initButtons() {
        // --- GROUP CONDITION ---
        AppCompatButton btnCond1 = findViewById(R.id.btnCondition1);
        AppCompatButton btnCond2 = findViewById(R.id.btnCondition2);
        AppCompatButton btnCond3 = findViewById(R.id.btnCondition3);

        // Masukkan ke list
        conditionButtons.add(btnCond1);
        conditionButtons.add(btnCond2);
        conditionButtons.add(btnCond3);

        // Pasang listener (aksi klik)
        View.OnClickListener conditionListener = v -> {
            AppCompatButton clickedBtn = (AppCompatButton) v;
            selectedCondition = clickedBtn.getText().toString(); // Simpan teks pilihan
            updateButtonStyles(conditionButtons, clickedBtn); // Update warna
        };
        btnCond1.setOnClickListener(conditionListener);
        btnCond2.setOnClickListener(conditionListener);
        btnCond3.setOnClickListener(conditionListener);

        // --- GROUP DURATION ---
        AppCompatButton btnDur1 = findViewById(R.id.btnDur1);
        AppCompatButton btnDur3 = findViewById(R.id.btnDur3);
        AppCompatButton btnDur5 = findViewById(R.id.btnDur5);
        AppCompatButton btnDur10 = findViewById(R.id.btnDur10);

        durationButtons.add(btnDur1);
        durationButtons.add(btnDur3);
        durationButtons.add(btnDur5);
        durationButtons.add(btnDur10);

        View.OnClickListener durationListener = v -> {
            AppCompatButton clickedBtn = (AppCompatButton) v;
            selectedDuration = clickedBtn.getText().toString();
            updateButtonStyles(durationButtons, clickedBtn);
        };
        btnDur1.setOnClickListener(durationListener);
        btnDur3.setOnClickListener(durationListener);
        btnDur5.setOnClickListener(durationListener);
        btnDur10.setOnClickListener(durationListener);

        // --- GROUP SOUND ---
        AppCompatButton btnSound1 = findViewById(R.id.btnSoundNature);
        AppCompatButton btnSound2 = findViewById(R.id.btnSoundCalm);

        soundButtons.add(btnSound1);
        soundButtons.add(btnSound2);

        View.OnClickListener soundListener = v -> {
            AppCompatButton clickedBtn = (AppCompatButton) v;
            selectedSound = clickedBtn.getText().toString();
            updateButtonStyles(soundButtons, clickedBtn);
        };
        btnSound1.setOnClickListener(soundListener);
        btnSound2.setOnClickListener(soundListener);
    }

    // Fungsi helper untuk mengubah warna tombol saat diklik
    private void updateButtonStyles(List<AppCompatButton> group, AppCompatButton selectedBtn) {
        for (AppCompatButton btn : group) {
            if (btn == selectedBtn) {
                // TOMBOL TERPILIH: Warna lebih gelap/berbeda
                // Menggunakan warna custom atau parsing hex code
                btn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.card_pink));
                // Atau hardcode warna jika R.color.card_pink tidak cocok:
                // btn.setBackgroundColor(0xFF004D73); // Warna biru gelap
            } else {
                // TOMBOL TIDAK TERPILIH: Kembali ke warna asal
                // Kita kembalikan ke warna biru default tombol Anda
                btn.setBackgroundTintList(null);
                btn.setBackgroundResource(R.drawable.bg_button_blue);
            }
        }
    }
}