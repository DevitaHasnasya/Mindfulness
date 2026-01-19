package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Beranda extends AppCompatActivity {
    CardView btnMindfulness, btnFocusFlow, btnYoga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        btnYoga = findViewById(R.id.btnYoga);


        btnMindfulness = findViewById(R.id.btnMindfulness);
        btnFocusFlow = findViewById(R.id.btnFocusFlow);

        btnMindfulness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, Mindfulness.class);
                startActivity(intent);
            }
        });
        btnFocusFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Beranda.this, FocusFlow.class);
                startActivity(intent);
            }
        });
        btnYoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Beranda.this, Yoga.class);
                startActivity(intent);
            }
        });
    }
}