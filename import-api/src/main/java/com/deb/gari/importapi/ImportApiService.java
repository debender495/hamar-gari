package com.deb.gari.importapi;

import com.deb.gari.model.bean.MapResult;

import java.io.InputStream;

/**
 * @author Debender Prasad
 */
public interface ImportApiService {

    /**
     * Import data and map to a new Platform
     *
     */
    MapResult importRecords(InputStream inputStream, String filename);
}
