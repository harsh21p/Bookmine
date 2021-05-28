package com.hny.bookmine;

public class Books {
    String author,title;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Books(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public Books() {
    }


}
