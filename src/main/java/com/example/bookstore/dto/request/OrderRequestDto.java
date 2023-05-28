package com.example.bookstore.dto.request;


import java.util.List;

public class OrderRequestDto {
    public OrderRequestDto(List<Integer> book_id, int user_id) {
        this.book_id = book_id;
        this.user_id = user_id;
    }

    public OrderRequestDto() {
    }

    public List<Integer> getBook_id() {
        return book_id;
    }

    public void setBook_id(List<Integer> book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    private List<Integer> book_id;
    private int user_id;
}
