package com.alamer.oracledbschemaexporter.service;


import com.alamer.oracledbschemaexporter.config.FileExtractorProperties;
import com.alamer.oracledbschemaexporter.dao.ExtractDDLDao;
import com.alamer.oracledbschemaexporter.model.SchemaModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class ExtractDDLServiceFileImpl implements ExtractDDLService {

    private final static Logger logger = LoggerFactory.getLogger(ExtractDDLServiceFileImpl.class);


    private final ExtractDDLDao extractDDLDao;
    private final FileExtractorProperties properties;

    public ExtractDDLServiceFileImpl(ExtractDDLDao extractDDLDao, FileExtractorProperties properties) {
        this.extractDDLDao = extractDDLDao;
        this.properties = properties;
    }


    @Override
    public void save() {
        logger.info("Start schema extractor");
        String currentSchemaName = extractDDLDao.getCurrentSchemaName();
        List<SchemaModel> schemaModels = extractDDLDao.extractSchemaDDl(currentSchemaName);
        schemaModels.forEach(this::saveDDLToFile);
        logger.info("Schema extraction complete");
    }

    private void saveDDLToFile(SchemaModel schemaModel) {
        Path file = Path.of(properties.getPath(), schemaModel.getObjectType(), schemaModel.getObjectName() + ".sql");
        if (!file.toFile().exists()) {
            file.toFile().mkdirs();
        }

        try {
            Files.deleteIfExists(file);
            Files.write(file, schemaModel.getExtractedDDL().getBytes(properties.getDbCharset()));
            logger.debug("Object type {} name {} ", schemaModel.getObjectType(), schemaModel.getObjectName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
