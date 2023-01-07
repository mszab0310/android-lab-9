package com.example.android_orm_lab9.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_orm_lab9.model.Person;
import com.example.android_orm_lab9.model.PersonDAO;

@Database(entities = {Person.class}, version = 2, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDAO personDAO();
}
