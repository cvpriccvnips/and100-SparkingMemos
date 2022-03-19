package com.example.cecilsflashcardapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

//@Database represent data base, flashcard entity
//entity is the object you want to store
@Database(entities = {Flashcard.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlashcardDao flashcardDao();
}
