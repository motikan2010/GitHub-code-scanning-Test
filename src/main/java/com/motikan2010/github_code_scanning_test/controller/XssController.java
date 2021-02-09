package com.motikan2010.github_code_scanning_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("xss")
public class XssController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView indexPage(@ModelAttribute("msg") String msg, ModelAndView model) {
        if ( msg != null ) {
            model.addObject("msg", msg);
        }
        model.setViewName("xss/index");
        return model;
    }

}
