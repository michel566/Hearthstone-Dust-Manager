package com.michelbarbosa.hsdm_hearthstonedustmanager.data.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.dao.stereotype.StereotypeDAO;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.Stereotype;

@Database(entities = {Stereotype.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract StereotypeDAO stereotypeDAO();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class,
                        "hsdm_database").build();

            }
        }
        return INSTANCE;
    }

}
