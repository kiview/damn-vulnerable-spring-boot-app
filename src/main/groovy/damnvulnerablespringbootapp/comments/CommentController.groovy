package damnvulnerablespringbootapp.comments

import org.springframework.boot.Banner
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

@Controller
class CommentController {

    private List comments = ["Hi super Webseite leider keine Mobile App deswegen nur einen Stern.", "Ich muss darauf hinweisen, dass eine Webseite ohne Impressum zu betreiben !!!strafbar!!! ist!!!", "Hallo, wir sinds! Wolltest du nicht die Fotos von Samstag hochladen? Grüße!"]

    @GetMapping("/")
    def home() {
        new ModelAndView(
                "views/comment",
                [bootVersion  : Banner.package.implementationVersion,
                 comments     : comments,
                 groovyVersion: GroovySystem.version])
    }

    @PostMapping("/")
    def postComment(@RequestParam String input) {
        new ModelAndView(
                "views/comment",
                [bootVersion  : Banner.package.implementationVersion,
                 comments     : [input] + comments,
                 groovyVersion: GroovySystem.version])
    }
}
