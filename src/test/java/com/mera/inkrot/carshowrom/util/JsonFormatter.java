package com.mera.inkrot.carshowrom.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFormatter {
    public static String getJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
