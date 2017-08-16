package de.gdaag.corp.rp.damnvulnerablespringbootapp

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

/**
 * TODO: Documentation
 */
@Controller
class HomeController {

    @GetMapping("/")
    def home() {
        new ModelAndView("views/home")
    }
}
