package com.ftn.sbnz.service.config.kie;

import java.io.IOException;
import java.io.InputStream;

import org.drools.core.io.impl.ClassPathResource;
import org.drools.decisiontable.ExternalSpreadsheetCompiler;

class TemplateExtractor {
    public static String extractTemplateAsDrl(String drtPath, String xlsPath, int startRow, int startCol)
            throws IOException {
        InputStream template = new ClassPathResource(drtPath).getInputStream();
        InputStream data = new ClassPathResource(xlsPath).getInputStream();
        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String templateDrl = converter.compile(data, template, startRow, startCol);
        return templateDrl;
    }

    public static String extractTemplateAsDrl(String drtPath, String xlsPath)
            throws IOException {
        InputStream template = new ClassPathResource(drtPath).getInputStream();
        InputStream data = new ClassPathResource(xlsPath).getInputStream();
        ExternalSpreadsheetCompiler converter = new ExternalSpreadsheetCompiler();
        String templateDrl = converter.compile(data, template, 2, 1);
        return templateDrl;
    }
}
