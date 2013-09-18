package br.com.unifrases.service;

import br.com.unifrases.model.Sentence;

/**
 * Created with IntelliJ IDEA.
 * User: alexandrelucchesi
 * Date: 9/16/13
 * Time: 6:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SentenceService {

    public void save(Sentence sentence);

    public Sentence findById(long id);

}
