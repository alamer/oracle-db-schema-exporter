package com.alamer.oracledbschemaexporter.model;


public class SchemaModel {
    String objectType;
    String objectName;
    String extractedDDL;

    public SchemaModel(String objectType, String objectName, String extractedDDL) {
        this.objectType = objectType;
        this.objectName = objectName;
        this.extractedDDL = extractedDDL;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getExtractedDDL() {
        return extractedDDL;
    }

    public void setExtractedDDL(String extractedDDL) {
        this.extractedDDL = extractedDDL;
    }
}
