package com.example.brij.myapplication.model;

/**
 * Created by Brij on 6/21/17.
 */

public class NewsItem {

    private String author;
    private String tittle;
    private String description;
    private String url;
    private String date;




    public NewsItem(String author, String tittle, String description, String url, String date) {

        this.author = author;
        this.tittle = tittle;
        this.description = description;
        this.url = url;
        this.date = date;

    }



    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public String getTittle() {
        return tittle;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }


}
