package com.groovycoder.dvsba.comments;

import groovy.lang.GroovySystem;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private List<String> comments = Arrays.asList("Hi super Webseite leider keine Mobile App deswegen nur einen Stern.", "Ich muss darauf hinweisen, dass eine Webseite ohne Impressum zu betreiben !!!strafbar!!! ist!!!", "Hallo, wir sinds! Wolltest du nicht die Fotos von Samstag hochladen? Grüße!");

    @GetMapping(path = "/")
    public ModelAndView listComments() {
        Map<String, Object> model = new HashMap<>();
        model.put("comments", comments);

        return new ModelAndView(
                "views/comment/comment",
                model);
    }

    @PostMapping("/")
    public ModelAndView postComment(@RequestParam String input) {
        comments.add(input);

        Map<String, Object> model = new HashMap<>();
        model.put("comments", comments);

        return new ModelAndView(
                "views/comment/comment",
                model);
    }

}
