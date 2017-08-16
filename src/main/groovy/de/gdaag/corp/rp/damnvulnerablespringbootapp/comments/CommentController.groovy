package de.gdaag.corp.rp.damnvulnerablespringbootapp.comments

import org.springframework.boot.Banner
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/comments")
class CommentController {

    private List comments = ["Hi super Webseite leider keine Mobile App deswegen nur einen Stern.", "Ich muss darauf hinweisen, dass eine Webseite ohne Impressum zu betreiben !!!strafbar!!! ist!!!", "Hallo, wir sinds! Wolltest du nicht die Fotos von Samstag hochladen? Grüße!"]

    @GetMapping(path = ["/"])
    def listComments() {
        new ModelAndView(
                "views/comment/comment",
                [bootVersion  : Banner.package.implementationVersion,
                 comments     : comments,
                 groovyVersion: GroovySystem.version])
    }

    @PostMapping("/")
    def postComment(@RequestParam String input) {
        comments = [input] + comments
        new ModelAndView(
                "views/comment/comment",
                [bootVersion  : Banner.package.implementationVersion,
                 comments     : comments,
                 groovyVersion: GroovySystem.version])
    }
}
