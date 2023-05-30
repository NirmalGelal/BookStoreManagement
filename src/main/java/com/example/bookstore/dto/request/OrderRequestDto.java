package com.example.bookstore.dto.request;


import java.util.List;

public class OrderRequestDto {
    public OrderRequestDto(List<Integer> bookIds, int userId) {
        this.bookIds = bookIds;
        this.userId = userId;
    }

    public OrderRequestDto() {
    }

    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private List<Integer> bookIds;
    private int userId;
}
