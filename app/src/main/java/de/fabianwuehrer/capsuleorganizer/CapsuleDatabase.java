package de.fabianwuehrer.capsuleorganizer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Capsule.class, version = 1)
public abstract class CapsuleDatabase extends RoomDatabase {

    private static CapsuleDatabase instance;

    public abstract CapsuleDao capsuleDao();

    public static synchronized CapsuleDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CapsuleDatabase.class, "capsule_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }

}
