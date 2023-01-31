package ru.netology.javacore;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InitJson {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("task")
    @Expose
    private String task;

    public String getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}
