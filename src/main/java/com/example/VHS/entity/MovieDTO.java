package com.example.VHS.entity;

public class MovieDTO {
    private String title;
    private String overview;

    public MovieDTO(String title, String overview) {
        this.title = title;
        this.overview = overview;
    }

    public MovieDTO() {
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
