package com.alamer.oracledbschemaexporter.dao;


import com.alamer.oracledbschemaexporter.model.SchemaModel;

import java.util.List;

public interface ExtractDDLDao {

    List<SchemaModel> extractSchemaDDl(String schema);
    String getCurrentSchemaName();
}
