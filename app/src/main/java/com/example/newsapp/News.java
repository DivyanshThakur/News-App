package com.example.newsapp;

public class News {
    private String title, author, url, imageUrl;

    public News(String title, String author, String url, String imageUrl) {
        this.title = title;
        this.author = author;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
