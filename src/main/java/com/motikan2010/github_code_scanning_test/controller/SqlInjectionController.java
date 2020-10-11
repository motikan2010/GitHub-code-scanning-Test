package com.motikan2010.github_code_scanning_test.controller;

import com.motikan2010.github_code_scanning_test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("sql-injection")
public class SqlInjectionController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView indexPage(@ModelAttribute("name") String name, ModelAndView model) {
        if ( name != null ) {
            model.addObject("persons", personService.findPersonById(name));
        }

        model.setViewName("sqli/index");
        return model;
    }

}
