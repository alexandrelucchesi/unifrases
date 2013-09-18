package br.com.unifrases.repository;

import br.com.unifrases.model.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcSentenceRepositoryImpl implements SentenceRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertSentence;

    private String value;

    @Autowired
    public JdbcSentenceRepositoryImpl(DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertSentence = new SimpleJdbcInsert(dataSource)
                .withTableName("tb_sentence")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Sentence findById(long id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        return this.namedParameterJdbcTemplate.queryForObject(
                "SELECT id, code, pt_br, en_us FROM tb_sentence WHERE id=:id",
                params,
                new ParameterizedRowMapper<Sentence>() {
                    @Override
                    public Sentence mapRow(ResultSet resultSet, int i) throws SQLException {
                        Sentence s = new Sentence();
                        s.setId(resultSet.getLong(1));
                        s.setCode(resultSet.getString(2));
                        for (int j = 3; j < resultSet.getMetaData().getColumnCount() + 1; j++)
                            if (!resultSet.getString(j).equals(""))
                                s.getSentences().put(resultSet.getMetaData().getColumnName(j), resultSet.getString(j));
                        return s;
                    }
                }
        );
    }

    @Override
    public List<Sentence> findByValue(String value) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Sentence> listAll() {
        return null;
    }

    @Override
    public void delete(Sentence sentence) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void save(Sentence sentence) {
        Number newKey = this.insertSentence.executeAndReturnKey(createSentenceParameterSource(sentence));
        sentence.setId(newKey.longValue());
        // TODO: Update behaviour and handle exceptions.
    }

    private MapSqlParameterSource createSentenceParameterSource(Sentence sentence) {
        MapSqlParameterSource mapSql = new MapSqlParameterSource();
        mapSql.addValue("code", sentence.getCode());
        for (String key : sentence.getSentences().keySet()) {
            mapSql.addValue(key, sentence.getSentences().get(key));
        }
        return mapSql;
    }

}
