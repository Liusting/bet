package com.example.blackoutsimulation.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseMessage {

    String operate;
    int responseCode;
    Info info;

    @Data
    public static class Info {
        String result;
        String detail;
    }

}
