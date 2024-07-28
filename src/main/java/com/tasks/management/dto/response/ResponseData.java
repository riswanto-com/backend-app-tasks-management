package com.tasks.management.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ResponseData <T> {
    private boolean status;
    private List<String> messages = new ArrayList<>();
    private T data;
    public boolean isStatus() {
        return status;
    }
    
}