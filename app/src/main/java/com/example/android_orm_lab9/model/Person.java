package com.example.android_orm_lab9.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    long uid;
    @ColumnInfo(name = "age")
    private int age;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "isActive")
    private boolean isActive;

    public Person() {
    }

    public Person(int age, String name, boolean isActive) {
        this.age = age;
        this.name = name;
        this.isActive = isActive;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @NonNull
    @Override
    public String toString() {
        return "Age: " + age + " " + name + " isActive: "+ isActive;
    }
}
