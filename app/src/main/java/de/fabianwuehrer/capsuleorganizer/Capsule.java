package de.fabianwuehrer.capsuleorganizer;

import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "capsule_table")
public class Capsule {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String description;
    private int cnt;
    private Date exp_date;

    public Capsule(String name, String description, int cnt, Date exp_date) {
        this.name = name;
        this.description = description;
        this.cnt = cnt;
        this.exp_date = exp_date;
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

    public Date getExp_date() {
        return exp_date;
    }
}
