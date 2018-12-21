package de.fabianwuehrer.capsuleorganizer;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CapsuleRepository {

    private CapsuleDao capsuleDao;
    private LiveData<List<Capsule>> allCapsules;

    public CapsuleRepository(Application application){
        CapsuleDatabase database = CapsuleDatabase.getInstance(application);
        capsuleDao = database.capsuleDao();
        allCapsules = capsuleDao.getAllCapsules();
    }

    public void insert(Capsule capsule){
        new InsertCapsuleAsyncTask(capsuleDao).execute(capsule);
    }

    public void update(Capsule capsule){
        new UpdateCapsuleAsyncTask(capsuleDao).execute(capsule);
    }

    public void delete(Capsule capsule){
        new DeleteCapsuleAsyncTask(capsuleDao).execute(capsule);
    }

    public LiveData<List<Capsule>> getAllCapsules(){
        return  allCapsules;
    }

    private static class InsertCapsuleAsyncTask extends AsyncTask<Capsule, Void, Void>{

        private CapsuleDao capsuleDao;

        private InsertCapsuleAsyncTask(CapsuleDao capsuleDao) {
            this.capsuleDao = capsuleDao;
        }

        @Override
        protected Void doInBackground(Capsule... capsules) {
            capsuleDao.insert(capsules[0]);
            return null;
        }
    }

    private static class UpdateCapsuleAsyncTask extends AsyncTask<Capsule, Void, Void>{

        private CapsuleDao capsuleDao;

        private UpdateCapsuleAsyncTask(CapsuleDao capsuleDao) {
            this.capsuleDao = capsuleDao;
        }

        @Override
        protected Void doInBackground(Capsule... capsules) {
            capsuleDao.update(capsules[0]);
            return null;
        }
    }

    private static class DeleteCapsuleAsyncTask extends AsyncTask<Capsule, Void, Void>{

        private CapsuleDao capsuleDao;

        private DeleteCapsuleAsyncTask(CapsuleDao capsuleDao) {
            this.capsuleDao = capsuleDao;
        }

        @Override
        protected Void doInBackground(Capsule... capsules) {
            capsuleDao.delete(capsules[0]);
            return null;
        }
    }
}
