package com.example.bookstore.dto.request;


import java.util.List;

public class OrderRequestDto {
    public OrderRequestDto(List<Integer> bookIds) {
        this.bookIds = bookIds;

    }
    public OrderRequestDto() {
    }
    public List<Integer> getBookIds() {
        return bookIds;
    }

    public void setBookIds(List<Integer> bookIds) {
        this.bookIds = bookIds;
    }

    private List<Integer> bookIds;
}
