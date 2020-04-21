package com.alamer.oracledbschemaexporter;

import com.alamer.oracledbschemaexporter.service.ExtractDDLService;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OracleDbSchemaExporterApplication implements CommandLineRunner {


    private final ExtractDDLService service;

    public OracleDbSchemaExporterApplication(ExtractDDLService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OracleDbSchemaExporterApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.save();
    }
}
