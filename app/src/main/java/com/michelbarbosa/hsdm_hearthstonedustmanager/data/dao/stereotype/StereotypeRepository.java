package com.michelbarbosa.hsdm_hearthstonedustmanager.data.dao.stereotype;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.michelbarbosa.hsdm_hearthstonedustmanager.data.dao.AppDatabase;
import com.michelbarbosa.hsdm_hearthstonedustmanager.data.domain.Stereotype;

import java.util.List;

class StereotypeRepository {

    private MutableLiveData<List<Stereotype>> mResult = new MutableLiveData<>();
    private LiveData<List<Stereotype>> mLiveData;
    private List<Stereotype> mData;
    private StereotypeDAO mDao;

    //Constructor Repository
    StereotypeRepository(Application application) {
        AppDatabase db;
        db = AppDatabase.getDatabase(application);
        mDao = db.stereotypeDAO();
        mLiveData = mDao.getAll();
        mData = getStereotype();
    }

    //Operations
    private List<Stereotype> getStereotype() {
        QueryAsyncTask task = new QueryAsyncTask(mDao);
        task.delegate = this;
        task.execute();
        return getmData();
    }

    void insertFromName(String stereotypeName){
        InsertFromNameAsyncTask task = new InsertFromNameAsyncTask(mDao);
        task.execute(stereotypeName);
    }

    void deleteFromName(String stereotypeName){
        DeleteFromNameTask task = new DeleteFromNameTask(mDao);
        task.execute(stereotypeName);
    }

    void deleteAll(){
        DeleteAsyncTask task = new DeleteAsyncTask(mDao);
        task.execute();
    }

    //Getters Setters and General structure repository class
    MutableLiveData<List<Stereotype>> getmResult() {
        return mResult;
    }

    LiveData<List<Stereotype>> getmLiveData() {
        return mLiveData;
    }

    List<Stereotype> getmData() {
        return mData;
    }

    void setmData(List<Stereotype> mData) {
        this.mData = mData;
    }

    private void asyncFinished(List<Stereotype> stereotypeList){
        mResult.setValue(stereotypeList);
        setmData(stereotypeList);
    }

    //Asynctasks
    private static class QueryAsyncTask extends AsyncTask<Void, Void, List<Stereotype>>{
        private StereotypeDAO at_dao;
        private StereotypeRepository delegate = null;
        QueryAsyncTask(StereotypeDAO at_dao) {
            this.at_dao = at_dao;
        }

        @Override
        protected  List<Stereotype> doInBackground(final Void... voids) {
            return at_dao.findList();
        }
        @Override
        protected void onPostExecute(List<Stereotype> stereotypeList) {
            if(stereotypeList != null && stereotypeList.isEmpty()){
                delegate.asyncFinished(stereotypeList);
            }
        }
    }

    private static class InsertFromNameAsyncTask extends AsyncTask<String, Void, Void>{
        private StereotypeDAO at_dao;
        InsertFromNameAsyncTask(StereotypeDAO at_dao) { this.at_dao = at_dao;}

        @Override
        protected Void doInBackground(String... strings) {
            at_dao.insert(new Stereotype(at_dao.count(),strings[0]));
            return null;
        }
    }

    private static class DeleteFromNameTask extends AsyncTask<String, Void, Void>{
        private StereotypeDAO at_dao;
        DeleteFromNameTask(StereotypeDAO at_dao) {
            this.at_dao = at_dao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            at_dao.deleteFromName(strings[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Void, Void, Void>{
        private StereotypeDAO at_dao;
        DeleteAsyncTask(StereotypeDAO at_dao) {
            this.at_dao = at_dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            at_dao.deleteAll();
            return null;
        }
    }

}
