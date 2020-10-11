package com.motikan2010.github_code_scanning_test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("xss")
public class XssController {

    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> indexPage(@ModelAttribute("msg") String msg) throws Exception {
        // Set Response body
        Map<String,String> payload = new HashMap<>();
        payload.put("message", msg);

        // Set Header
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "text/html");
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(payload), headers, HttpStatus.OK);
    }

}