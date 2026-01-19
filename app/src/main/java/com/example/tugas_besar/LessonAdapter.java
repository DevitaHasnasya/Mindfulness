package com.example.tugas_besar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    private List<LessonItem> dataList;
    private Context context;

    public LessonAdapter(List<LessonItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LessonItem item = dataList.get(position);

        // Pasang Judul & Gambar
        holder.tvTitle.setText(item.getTitle());
        holder.containerLayout.setBackgroundColor(ContextCompat.getColor(context, item.getColorRes()));
        holder.imgIllustration.setImageResource(item.getImageRes());


        holder.btnStart.setOnClickListener(v -> {

            String judul = item.getTitle();

            if (judul.equals("Introduction to Mindfulness")) {
                Intent intent = new Intent(context, DetailMindfulness.class);
                context.startActivity(intent);
            }
            else if (judul.equals("Managing Health Anxiety & Panic")) {
                Intent intent = new Intent(context, DetailAnxiety.class);
                context.startActivity(intent);
            }
            else if (judul.equals("Managing Stress & Overthinking")) {
                Intent intent = new Intent(context, DetailInsomnia.class);
                context.startActivity(intent);
            }
            else if (judul.equals("Managing Insomnia & Sleep Disorder")) {
                Intent intent = new Intent(context, DetailInsomnia.class);
                context.startActivity(intent);
            }
            else {
                Toast.makeText(context, "Materi belum tersedia", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, btnStart;
        ImageView imgIllustration;
        ConstraintLayout containerLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnStart = itemView.findViewById(R.id.btnStart);
            imgIllustration = itemView.findViewById(R.id.imgIllustration);
            containerLayout = itemView.findViewById(R.id.containerLayout);
        }
    }
}