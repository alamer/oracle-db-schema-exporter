package com.alamer.oracledbschemaexporter.dao;

import com.alamer.oracledbschemaexporter.model.SchemaModel;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;


@Repository
public class ExtractDDLDaoImpl extends ResourceSqlDao implements ExtractDDLDao {

    private final JdbcTemplate jdbcTemplate;

    private String sqlSelect;

    public ExtractDDLDaoImpl(JdbcTemplate jdbcTemplate, ResourcePatternResolver resourcePatternResolver) {
        super(resourcePatternResolver);
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() throws IOException {
        sqlSelect = loadSqlFromResource("extract_ddl.sql");
    }

    @Override
    public List<SchemaModel> extractSchemaDDl(String schema) {
        return jdbcTemplate.query(sqlSelect.replace("{{ schema }}",schema), (rs, i) -> new SchemaModel(rs.getString("OBJECT_TYPE"),
                rs.getString("NAME"), rs.getString("TEXT")));
    }

    @Override
    public String getCurrentSchemaName() {
        return jdbcTemplate.queryForObject("select USERNAME from user_users",String.class);
    }
}
