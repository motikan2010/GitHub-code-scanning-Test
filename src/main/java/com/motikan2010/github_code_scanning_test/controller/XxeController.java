package com.motikan2010.github_code_scanning_test.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "xxe")
public class XxeController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> xxe(@RequestParam(value = "data") String data) throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(new InputSource(new StringReader(data)));
        NodeList RegistrationNo = doc.getElementsByTagName("hoge");
        String result = RegistrationNo.item(0).getFirstChild().getNodeValue();

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("result", result);
        return new ResponseEntity<>(responseMap, new HttpHeaders(), HttpStatus.OK);
    }

}
