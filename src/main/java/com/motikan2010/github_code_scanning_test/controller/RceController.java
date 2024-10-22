package com.motikan2010.github_code_scanning_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
@RequestMapping("rce")
public class RceController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(@RequestParam("iq") String iq) throws Exception {
        Runtime runtime = Runtime.getRuntime();
        String cmd = "ping -c 4 " + iq;
        System.out.println(cmd);
        Process p = runtime.exec(cmd);
        p.waitFor();

        StringBuilder out = new StringBuilder();
        try ( BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream())) ) {
            String line;
            while ( (line = br.readLine()) != null ) {
                out.append(line + "\n");
            }
        }
        return new String(out);
    }

}
