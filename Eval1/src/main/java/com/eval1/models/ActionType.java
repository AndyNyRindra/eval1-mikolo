package com.eval1.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActionType {

    private String name;
    private String url;

    public ActionType(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
