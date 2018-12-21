package de.fabianwuehrer.capsuleorganizer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "capsule_table")
public class Capsule {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private int count;

    public Capsule(String name, String description, int count) {
        this.name = name;
        this.description = description;
        this.count = count;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }
}
