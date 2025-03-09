package org.example;

public class Book {
    String title;
    String isbn;
    String author;

    public Book(String title, String isbn, String author){
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }
}
