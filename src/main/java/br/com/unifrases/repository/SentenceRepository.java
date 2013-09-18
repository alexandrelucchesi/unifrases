package br.com.unifrases.repository;

/**
 * Created with IntelliJ IDEA.
 * User: alexandrelucchesi
 * Date: 9/13/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */

import br.com.unifrases.model.Sentence;

import java.util.List;

public interface SentenceRepository {

    public void save(Sentence sentence);

    public Sentence findById(long id);

    public List<Sentence> findByValue(String value);

    public List<Sentence> listAll();

    public void delete(Sentence sentence);

}
