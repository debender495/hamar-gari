package com.deb.gari.adapter.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Debender Prasad
 */
@RestController
@RequestMapping(value = "api/current")
@Api(value = "/api/current", description = "Custom Controller")
public class CustomController {

    @PostMapping(value = "/custom")
    @ApiOperation(value = "Get custom response")
    public String custom() {
        return "custom";
    }
}