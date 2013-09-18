package br.com.unifrases.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Sentence {

    private Long id;

    private String code;

    private Map<String, String> sentences;

    public Sentence() {
        sentences = new HashMap<String, String>();
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getSentences() {
        return sentences;
    }

    public void setSentences(Map<String, String> sentences) {
        this.sentences = sentences;
    }

}
