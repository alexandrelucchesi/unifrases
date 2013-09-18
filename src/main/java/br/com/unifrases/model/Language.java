package br.com.unifrases.model;

import br.com.unifrases.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class Language {

    /* Maps a 'locale' (String) to its corresponding textual descriptions in many languages. */
    /*
     Eg.:
        {
            "pt_br" : {
                "pt_br" : "Português (Brasil)",
                "en_us" : "Portuguese (Brazil)
            }
        }
     */
    private Map<String, Map<String, String>> descriptions;

    public Language() {
        descriptions = new HashMap<String, Map<String, String>>();
    }

    public Map<String, Map<String, String>> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(Map<String, Map<String, String>> descriptions) {
        this.descriptions = descriptions;
    }

}
