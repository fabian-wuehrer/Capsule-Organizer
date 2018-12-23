package de.fabianwuehrer.capsuleorganizer;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CapsuleViewModel extends AndroidViewModel {

    private CapsuleRepository repository;
    private LiveData<List<Capsule>> allCapsules;

    public CapsuleViewModel(@NonNull Application application) {
        super(application);
        repository = new CapsuleRepository(application);
        allCapsules = repository.getAllCapsules();
    }

    public void insert(Capsule capsule){
        repository.insert(capsule);
    }

    public void update(Capsule capsule){
        repository.update(capsule);
    }

    public void delete(Capsule capsule){
        repository.delete(capsule);
    }

    public LiveData<List<Capsule>> getAllCapsules() {
        return allCapsules;
    }

    public LiveData<Integer> sum_cnt(){
        return repository.sum_cnt();
    }

}
