package com.deb.gari.model.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Debender Prasad
 */
@Getter
@AllArgsConstructor
public class MapResult {

    private Map<String, Object> resultObjectMap;

    public MapResult() {
        resultObjectMap = new HashMap<>();
    }
}
