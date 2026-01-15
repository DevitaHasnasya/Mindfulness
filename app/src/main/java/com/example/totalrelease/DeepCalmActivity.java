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

public class DeepCalmActivity extends AppCompatActivity {

    private boolean isMuted = false;
    private String selectedCondition = null;
    private String selectedSound = null;

    // List tombol untuk grouping
    private List<AppCompatButton> conditionButtons = new ArrayList<>();
    private List<AppCompatButton> soundButtons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deep_calm);

        setupHeader();
        initChoiceButtons();
        setupStartButton();
    }

    private void setupHeader() {
        // Tombol Close
        ImageView btnClose = findViewById(R.id.btnClosePage);
        btnClose.setOnClickListener(v -> finish());

        // Tombol Audio Toggle
        ImageView btnAudio = findViewById(R.id.btnAudioDetail);
        btnAudio.setOnClickListener(v -> {
            if (isMuted) {
                isMuted = false;
                btnAudio.setImageResource(R.drawable.low);
                Toast.makeText(this, "Audio ON", Toast.LENGTH_SHORT).show();
            } else {
                isMuted = true;
                btnAudio.setImageResource(R.drawable.mute);
                Toast.makeText(this, "Audio MUTED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initChoiceButtons() {
        // 1. Group Condition
        AppCompatButton btnCond1 = findViewById(R.id.btnCondDeepBreath);
        AppCompatButton btnCond2 = findViewById(R.id.btnCondUnknot);

        conditionButtons.add(btnCond1);
        conditionButtons.add(btnCond2);

        View.OnClickListener condListener = v -> {
            AppCompatButton clicked = (AppCompatButton) v;
            selectedCondition = clicked.getText().toString();
            updateButtonStyles(conditionButtons, clicked);
        };
        btnCond1.setOnClickListener(condListener);
        btnCond2.setOnClickListener(condListener);

        // 2. Group Sound
        AppCompatButton btnSound1 = findViewById(R.id.btnSoundAqua);
        AppCompatButton btnSound2 = findViewById(R.id.btnSoundForest);
        AppCompatButton btnSound3 = findViewById(R.id.btnSoundCalming);

        soundButtons.add(btnSound1);
        soundButtons.add(btnSound2);
        soundButtons.add(btnSound3);

        View.OnClickListener soundListener = v -> {
            AppCompatButton clicked = (AppCompatButton) v;
            selectedSound = clicked.getText().toString();
            updateButtonStyles(soundButtons, clicked);
        };
        btnSound1.setOnClickListener(soundListener);
        btnSound2.setOnClickListener(soundListener);
        btnSound3.setOnClickListener(soundListener);
    }

    private void setupStartButton() {
        AppCompatButton btnStart = findViewById(R.id.btnStartDeepCalm);
        btnStart.setOnClickListener(v -> {
            // Di dalam onClick tombol Start:
            if (selectedCondition == null) {
                Toast.makeText(this, "Please choose a condition first", Toast.LENGTH_SHORT).show();
            } else if (selectedSound == null) {
                Toast.makeText(this, "Please choose a sound", Toast.LENGTH_SHORT).show();
            } else {
                // Arahkan ke Activity Timer KHUSUS Deep Calm
                Intent intent = new Intent(DeepCalmActivity.this, TimerDeepCalmActivity.class);
                // Tidak perlu kirim ID gambar lagi karena sudah dipasang paten di XML
                startActivity(intent);
            }
        });
    }

    private void updateButtonStyles(List<AppCompatButton> group, AppCompatButton selected) {
        for (AppCompatButton btn : group) {
            if (btn == selected) {
                // Ubah warna jadi Pink saat dipilih
                btn.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.card_pink));
            } else {
                // Reset warna jadi Biru
                btn.setBackgroundTintList(null);
                btn.setBackgroundResource(R.drawable.bg_button_blue);
            }
        }
    }
}