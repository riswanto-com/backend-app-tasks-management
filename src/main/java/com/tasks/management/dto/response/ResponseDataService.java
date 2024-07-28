package com.tasks.management.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDataService<T> {
    private boolean status;
    private String messages;
    private List<String> data = new ArrayList<>();

    public boolean isStatus() {
        return status;
    }

}