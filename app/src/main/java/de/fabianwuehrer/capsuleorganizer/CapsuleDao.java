package de.fabianwuehrer.capsuleorganizer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface CapsuleDao {

    @Insert
    void insert(Capsule capsule);

    @Update
    void update(Capsule capsule);

    @Delete
    void delete(Capsule capsule);
}
