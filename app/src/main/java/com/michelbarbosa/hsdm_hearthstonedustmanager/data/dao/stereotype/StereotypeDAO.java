package com.michelbarbosa.hsdm_hearthstonedustmanager.data.dao.stereotype;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.Stereotype;

import java.util.List;

@Dao
public interface StereotypeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Stereotype stereotype);

    @Query("SELECT * FROM stereotype")
    List<Stereotype> findList();

    @Query("SELECT * FROM stereotype")
    LiveData<List<Stereotype>> getAll();

    @Query("SELECT COUNT (*) FROM stereotype")
    int count();

    @Query("DELETE FROM Stereotype WHERE name = :name")
    void deleteFromName(String name);

    @Query("DELETE FROM stereotype")
    void deleteAll();

}
