package br.com.unifrases.repository;

import br.com.unifrases.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcLanguageRepositoryImpl implements LanguageRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcLanguageRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Language selectDescriptions() {
        List<Map<String, Map<String, String>>> languageList = this.jdbcTemplate.query(
                "SELECT * FROM tb_language",
                new ParameterizedRowMapper<Map<String, Map<String, String>>>() {
                    @Override
                    public Map<String, Map<String, String>> mapRow(ResultSet resultSet, int i) throws SQLException {
                        Map<String, String> descriptions = new HashMap<String, String>();
                        String locale = resultSet.getString(1);
                        for (int j = 2; j <= resultSet.getMetaData().getColumnCount(); j++) {
                            String s = resultSet.getString(j);
                            if ((!s.equals("")) && (s != null))
                                descriptions.put(resultSet.getMetaData().getColumnName(j), resultSet.getString(j));
                        }
                        Map<String, Map<String, String>> lang_desc = new HashMap<String, Map<String, String>>();
                        lang_desc.put(locale, descriptions);
                        return lang_desc;
                    }
                }
        );

        Map<String, Map<String, String>> lang_desc = new HashMap<String, Map<String, String>>();
        for (Map<String, Map<String, String>> row : languageList) {
            lang_desc.putAll(row);
        }

        Language language = new Language();
        language.setDescriptions(lang_desc);

        return language;
    }

}
