package com.michelbarbosa.hsdm_hearthstonedustmanager.data.dao.stereotype;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.Stereotype;

import java.util.List;

public class StereotypeViewModel extends AndroidViewModel {

    private StereotypeRepository repository;
    private LiveData<List<Stereotype>> allData;
    private MutableLiveData<List<Stereotype>> result;

    public StereotypeViewModel(@NonNull Application application) {
        super(application);
        repository = new StereotypeRepository(application);
        allData = repository.getmLiveData();
        result = repository.getmResult();
    }

    public LiveData<List<Stereotype>> getAllData() {
        return allData;
    }

    public MutableLiveData<List<Stereotype>> getResult() {
        return result;
    }

    public void insert(String stereotypeName){
        repository.insertFromName(stereotypeName);
    }

    public void insertSetDefaultValues(String... values){
        for (String name : values) {
            repository.insertFromName(name);
        }
    }

    public void wipe(){
        repository.deleteAll();
    }

    public void deleteFromName(String stereotypeName){
        repository.deleteFromName(stereotypeName);
    }

}
