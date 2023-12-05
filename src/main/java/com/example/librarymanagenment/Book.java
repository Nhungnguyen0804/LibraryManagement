package com.example.librarymanagenment;
import java.sql.Date;

public class Book {
    private final String bookId;
    private final String title;
    private final String author;
    private final String image;
    private final String nxb;
    private final String chuDe;
    public Book(String bookId, String title, String author,String nxb, String chuDe, String image  ){
        this.title = title;
        this.author = author;
        this.bookId = bookId;
        this.nxb = nxb;
        this.chuDe = chuDe;
        this.image = image;

    }

    public String getTitle(){
        return title;
    }
    public String getAuthor(){
        return author;
    }
    public String getImage(){
        return image;
    }

    public String getBookId() {
        return bookId;
    }

    public String getNxb() {return nxb;}
    public String getChuDe(){return chuDe;}
}
