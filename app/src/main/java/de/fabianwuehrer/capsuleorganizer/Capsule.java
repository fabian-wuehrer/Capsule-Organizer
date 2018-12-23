package de.fabianwuehrer.capsuleorganizer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "capsule_table")
public class Capsule {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private int cnt;

    public Capsule(String name, String description, int cnt) {
        this.name = name;
        this.description = description;
        this.cnt = cnt;
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

    public int getCnt() {
        return cnt;
    }
}
