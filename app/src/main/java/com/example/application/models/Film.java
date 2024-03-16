package com.example.application.models;

public class Film {

    private String title;
    private String genre;
    private int year;
    private boolean selected;

    public Film(String title, String genre, int year, boolean selected) {
        this.selected = selected;
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public String toString() {
        return title + '/' + genre +  "/" + year;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
