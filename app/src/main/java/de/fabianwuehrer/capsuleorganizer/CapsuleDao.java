package de.fabianwuehrer.capsuleorganizer;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CapsuleDao {
    @Insert
    void insert(Capsule capsule);

    @Update
    void update(Capsule capsule);

    @Delete
    void delete(Capsule capsule);

    @Query("SELECT * FROM capsule_table ORDER BY CASE WHEN cnt<=0 then 1 else 0 end, name COLLATE NOCASE")
    LiveData<List<Capsule>> getAllCapsules();

    @Query("SELECT SUM(cnt) FROM capsule_table")
    LiveData<Integer> sum_cnt();

}
