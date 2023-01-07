package com.example.android_orm_lab9.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PersonDAO {
    @Query("SELECT * FROM person")
    List<Person> getAll();


    @Query("SELECT * FROM person WHERE name LIKE :name")
    Person findByName(String name);

    @Insert
    void insertAll(Person... persons);

}
