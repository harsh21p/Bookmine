package com.example.bookmine;

public class Book1 {
    String title,cover_link,author,genre_and_votes,number_of_pages;

    public Book1() {
    }

    public Book1(String title, String cover_link, String author, String genre_and_votes, String number_of_pages) {
        this.title = title;
        this.cover_link = cover_link;
        this.author = author;
        this.genre_and_votes = genre_and_votes;
        this.number_of_pages = number_of_pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover_link() {
        return cover_link;
    }

    public void setCover_link(String cover_link) {
        this.cover_link = cover_link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre_and_votes() {
        return genre_and_votes;
    }

    public void setGenre_and_votes(String genre_and_votes) {
        this.genre_and_votes = genre_and_votes;
    }

    public String getNumber_of_pages() {
        return number_of_pages;
    }

    public void setNumber_of_pages(String number_of_pages) {
        this.number_of_pages = number_of_pages;
    }
}
