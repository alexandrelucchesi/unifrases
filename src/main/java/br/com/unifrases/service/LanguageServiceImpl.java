package br.com.unifrases.service;

import br.com.unifrases.model.Language;
import br.com.unifrases.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageServiceImpl implements LanguageService {

    private LanguageRepository languageRepository;

    @Autowired
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public Language getDescriptions() {
        return languageRepository.selectDescriptions();
    }

}
