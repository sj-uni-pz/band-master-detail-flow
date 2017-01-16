package com.android.me.bandmasterdetail;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ME on 1/5/2017.
 */

public class Band implements Serializable {

    private long id;
    private String name;
    private String genre;
    private String description;

    public Band(String name, String genre, String description) {
        this.name = name;
        this.genre = genre;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                 name;

    }


}
