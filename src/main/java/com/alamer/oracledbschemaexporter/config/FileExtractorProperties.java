package com.alamer.oracledbschemaexporter.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "extractor.file")
public class FileExtractorProperties {

    String path;
    String dbCharset;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDbCharset() {
        return dbCharset;
    }

    public void setDbCharset(String dbCharset) {
        this.dbCharset = dbCharset;
    }
}
