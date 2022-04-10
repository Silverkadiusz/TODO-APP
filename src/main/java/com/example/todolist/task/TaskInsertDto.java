package com.example.todolist.task;

//klasa odpowiedzialna za dodawania tylko i wyłącznie
public class TaskInsertDto {
    private String name;

    private Long categoryId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
