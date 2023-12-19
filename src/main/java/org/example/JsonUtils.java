package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static MyRecord convertJsonToMyRecord(String json) {
        try {
            return objectMapper.readValue(json, MyRecord.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to MyRecord", e);
        }
    }

//    private static final ObjectMapper objectMapper = new ObjectMapper();
//
//    public static List<MyRecord> convertJsonToMyRecords(String json) {
//        try {
//            return objectMapper.readValue(json, new TypeReference<List<MyRecord>>() {});
//        } catch (IOException e) {
//            throw new RuntimeException("Error converting JSON to MyRecords", e);
//        }
//    }



}
