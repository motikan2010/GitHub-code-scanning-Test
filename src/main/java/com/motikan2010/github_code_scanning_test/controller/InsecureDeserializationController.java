package com.motikan2010.github_code_scanning_test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import java.io.*;
import java.util.Base64;

@Controller
@RequestMapping("insecure-deserialization")
public class InsecureDeserializationController {

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public ResponseEntity<String> get(@RequestParam("name") String name, @RequestParam("age") String age) throws IOException {

        // Create object from Person class. Serialize object.
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(new Person(name, Integer.parseInt(age)));

        // Encode serialized object to Base64.
        String serializedBase64 = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        return new ResponseEntity<>(serializedBase64, HttpStatus.OK);
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.POST)
    public ResponseEntity<String> top(ServletRequest request) {
        Person p = null;
        try {
            ServletInputStream sis = request.getInputStream();
            ObjectInputStream oin = new ObjectInputStream(sis);
            p = (Person)oin.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(p.showInfo(), HttpStatus.OK);
    }

}

class Person implements Serializable {

    private static final long serialVersionUID = -7053655917805742728L;
    public String name;
    public int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String showInfo() {
        String result = "Person [name = " + this.name + ", age = " + this.age + "]";
        System.out.println(result);
        return result;
    }
}
