package com.motikan2010.github_code_scanning_test.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "directory-traversal")
public class DirectoryTraversalController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> nioRead(@RequestParam(value = "file", required = false) String fileName) throws IOException {
        String result = Files.lines(Paths.get("src/main/resources/static/" + fileName), StandardCharsets.UTF_8)
                .collect(Collectors.joining("\n"));

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("param", result);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

}
