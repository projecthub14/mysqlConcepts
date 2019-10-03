//package com.example.demo.jpa;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//
//@Entity
//@Table(name="book")
//public class Book {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer bookId;
//
//    @Column(name="title")
//    private String title;
//
//    @Column(name="isbn")
//    private String isbn;
//
//    @ManyToMany
//    @JoinTable(name = "book_author" , joinColumns= @JoinColumn(name="book_id"),
//            inverseJoinColumns = @JoinColumn(name="author_id"))
//    private ArrayList<Author> authors = new ArrayList<>();
//
//    public Integer getBookId() {
//        return bookId;
//    }
//
//    public void setBookId(Integer bookId) {
//        this.bookId = bookId;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getIsbn() {
//        return isbn;
//    }
//
//    public void setIsbn(String isbn) {
//        this.isbn = isbn;
//    }
//
//    public ArrayList<Author> getAuthors() {
//        return authors;
//    }
//
//    public void setAuthors(ArrayList<Author> authors) {
//        this.authors = authors;
//    }
//}
