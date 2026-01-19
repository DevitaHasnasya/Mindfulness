package com.example.tugas_besar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Mindfulness extends AppCompatActivity {

    RecyclerView recyclerView;
    LessonAdapter adapter;
    List<LessonItem> listPelajaran;
    ImageView btnClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindfulness);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listPelajaran = new ArrayList<>();

        // Kartu 1 (Pink)
        listPelajaran.add(new LessonItem(
                "Introduction to Mindfulness",
                R.color.card_pink,
                R.drawable.intro));

        // Kartu 2 (Biru)
        listPelajaran.add(new LessonItem(
                "Managing Health Anxiety & Panic",
                R.color.card_blue,
                R.drawable.intro));

        // Kartu 3 (Pink)
        listPelajaran.add(new LessonItem(
                "Managing Insomnia & Sleep Disorder",
                R.color.card_pink,
                R.drawable.intro));

        // Kartu 3 (Biru)
        listPelajaran.add(new LessonItem(
                "Managing Stress & Overthinking",
                R.color.card_blue,
                R.drawable.intro));
//      //adapter
        adapter = new LessonAdapter(listPelajaran, this);
        recyclerView.setAdapter(adapter);

        btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}