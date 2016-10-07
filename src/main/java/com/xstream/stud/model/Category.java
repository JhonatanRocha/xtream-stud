package com.xstream.stud.model;

public class Category {

	private Category father;
    private String name;

    public Category(Category father, String name) {
        this.father = father;
        this.name = name;
    }

    public void setFather(Category father) {
        this.father = father;
    }
}
