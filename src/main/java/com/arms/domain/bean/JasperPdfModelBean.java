package com.arms.domain.bean;//package com.arms.domain.bean;


import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arms_matsushita on 西暦2016/05/13.
 */
public class JasperPdfModelBean extends AbstractView {

    private static final String CONTENT_TYPE = "application/pdf";
    private String jrxmlPath;
    private String fileName;
    private String jasperPath;
    private List<?> beanList;

    public JasperPdfModelBean(String jrxmlPath, List<?> beanList, String fileName, String jasperPath) {
        this.jrxmlPath = jrxmlPath;
        this.beanList = beanList;
        this.fileName = fileName;
        this.jasperPath = jasperPath;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try (InputStream template = ClassLoader.class.getResourceAsStream(jrxmlPath)) {
            JasperReport report = JasperCompileManager.compileReport(jrxmlPath);
            if (fileName != null && !fileName.isEmpty()) {
                response.setContentType(getContentType());
                response.setHeader("content-disposition", "attachment;filename=" + fileName + ".pdf");
            }
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("emptyDataSource", new JREmptyDataSource());
            params.put("jasperPath", jasperPath);
            JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(beanList));
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        }
    }

    public InputStream toInputStream() throws Exception {
        try (InputStream template = ClassLoader.class.getResourceAsStream(jrxmlPath)) {
            JasperReport report = JasperCompileManager.compileReport(jrxmlPath);
            final Map<String, Object> params = new HashMap<String, Object>();
            params.put("emptyDataSource", new JREmptyDataSource());
            params.put("jasperPath", jasperPath);
            JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(beanList));
            byte[] pdf = JasperExportManager.exportReportToPdf(print);
            return new ByteArrayInputStream(pdf);
        }
    }
}

