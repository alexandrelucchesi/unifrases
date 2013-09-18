package br.com.unifrases.web;

import br.com.unifrases.model.Sentence;
import br.com.unifrases.service.LanguageService;
import br.com.unifrases.service.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SentenceController {

    private LanguageService languageService;

    private SentenceService sentenceService;

    @Autowired
    public SentenceController(LanguageService languageService, SentenceService sentenceService) {
        this.languageService = languageService;
        this.sentenceService = sentenceService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello world!");
        return "index";
    }

    @RequestMapping(value = "/sentence/{id}", method = RequestMethod.GET)
    public String processSearchById(ModelMap model, @PathVariable long id) {
        Sentence sentence = sentenceService.findById(id);
        model.addAttribute("sentence", sentence);
        return "sample";
    }

    @RequestMapping(value = "/sentence/new", method = RequestMethod.GET)
    public String getFormSentenceAdd(Model model) {
        model.addAttribute("languageDesc", languageService.getDescriptions().getDescriptions());
        return "management";
    }

}
