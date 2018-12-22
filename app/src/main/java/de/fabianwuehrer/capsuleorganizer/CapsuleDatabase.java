package de.fabianwuehrer.capsuleorganizer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Capsule.class, version = 1)
public abstract class CapsuleDatabase extends RoomDatabase {

    private static CapsuleDatabase instance;

    public abstract CapsuleDao capsuleDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
        }
    };

    public static synchronized CapsuleDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), CapsuleDatabase.class, "capsule_database")
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return  instance;
    }

}
