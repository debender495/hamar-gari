package com.deb.gari.exportapi;

import com.deb.gari.model.bean.MapResult;

import java.io.InputStream;

/**
 * @author Debender Prasad
 */
public interface ExportApiService {

    /**
     * Exports the data to an specific format.
     *
     * @return The processed validated report data
     * @param mapResult
     */
    InputStream exportValidatedReport(MapResult mapResult) throws Exception;
}
