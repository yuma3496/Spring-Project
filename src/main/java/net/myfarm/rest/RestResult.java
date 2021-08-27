package net.myfarm.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class RestResult {

    // Return code
    private int result;

    /** Error map
     *  key: field name
     *  value: Error message
     */

    private Map<String, String> errors;
}
