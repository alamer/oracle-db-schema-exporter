package com.alamer.oracledbschemaexporter.dao;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.nio.file.Files;

public class ResourceSqlDao {

    private final ResourcePatternResolver resourcePatternResolver;

    public ResourceSqlDao(ResourcePatternResolver resourcePatternResolver) {
        this.resourcePatternResolver = resourcePatternResolver;
    }

    protected String loadSqlFromResource(String sqlFileName) throws IOException {
        Resource resource = resourcePatternResolver.getResource("classpath:sql/" + sqlFileName);
        return Files.readString(resource.getFile().toPath());
    }

}
