package br.com.unifrases.service;

import br.com.unifrases.model.Sentence;
import br.com.unifrases.repository.SentenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SentenceServiceImpl implements SentenceService {

    private final SentenceRepository sentenceRepository;

    @Autowired
    public SentenceServiceImpl(SentenceRepository sentenceRepository) {
        this.sentenceRepository = sentenceRepository;
    }

    @Override
    public void save(Sentence sentence) {
        sentenceRepository.save(sentence);
    }

    @Override
    @Transactional(readOnly = true)
    public Sentence findById(long id) {
        return sentenceRepository.findById(id);
    }

}
