package com.patterns.model;

import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public ObjectForMessage clone() {
        ObjectForMessage instance = new ObjectForMessage();
        instance.setData(List.copyOf(this.data));
        return instance;
    }
}
