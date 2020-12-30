package com.yi.easycode.commons.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author yizhicheng
 * @ClassName GenerateUtil
 * @Description 生成代码，核心处理类 Freemarker
 * @Date 2020/12/20 4:55 下午
 **/
@Slf4j
public class GenerateUtil {

    public static void generateCode(List<Map<String, Object>> datas) {
        Writer out = null;
        try {
            Configuration freemarkerConfiguration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            freemarkerConfiguration.setClassForTemplateLoading(GenerateUtil.class, "/");
            for (Map dataMap:datas) {
                String templateName  = (String) dataMap.get("templateName");
                String className  = (String) dataMap.get("className");
                String packageName  = (String) dataMap.get("packageName");
                Template template = freemarkerConfiguration.getTemplate("templates/"+templateName);
                File tmpFile = new File("/Users/yi/Desktop/code");
                if (!tmpFile.exists()) {
                    tmpFile.mkdirs();
                }
                String filePackageName = packageName.replace(".","/");
                File packageFile = new File(tmpFile+"/"+filePackageName);
                if (!packageFile.exists()) {
                    packageFile.mkdirs();
                }
                File docFile = new File( packageFile+ "/"+className+".java");
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
                template.process(dataMap, out);
            }
            log.info("文件创建成功 !");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
