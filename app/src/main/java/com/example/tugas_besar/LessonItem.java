package com.example.tugas_besar;

public class LessonItem {
    String title;
    int colorRes; // ID Warna (Pink/Biru)
    int imageRes; // ID Gambar


    public LessonItem(String title, int colorRes, int imageRes) {
        this.title = title;
        this.colorRes = colorRes;
        this.imageRes = imageRes;
    }

    // mengambil data
    public String getTitle() { return title; }
    public int getColorRes() { return colorRes; }
    public int getImageRes() { return imageRes; }
}