package com.board.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/Board/*")
public class BoardController {

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list(Locale locale, Model model) {
        
    }
    
}
