package com.example.bookstore.dto.request;

public class OrderRequestDto {
    public OrderRequestDto(int book_id, int user_id) {
        this.book_id = book_id;
        this.user_id = user_id;
    }

    public OrderRequestDto() {
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private int book_id;
    private int user_id;
}
