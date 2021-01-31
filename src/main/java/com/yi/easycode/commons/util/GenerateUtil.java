package com.yi.easycode.commons.util;

import cn.hutool.core.util.ZipUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static List<Map<String, byte[]>> generateCode(List<Map<String, Object>> datas) {
        Writer out = null;
        List<Map<String, byte[]>> compressInfoList = new ArrayList<>();
        try {
            Configuration freemarkerConfiguration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
            freemarkerConfiguration.setClassForTemplateLoading(GenerateUtil.class, "/");
            for (Map dataMap:datas) {
                String templateName  = (String) dataMap.get("templateName");
                String className  = (String) dataMap.get("className");
                String packageName  = (String) dataMap.get("packageName");
                Template template = freemarkerConfiguration.getTemplate("templates/"+templateName);
//                File tmpFile = new File("/Users/yi/Desktop/code");
//                if (!tmpFile.exists()) {
//                    tmpFile.mkdirs();
//                }
                String filePackageName = packageName.replace(".","/");
//                File packageFile = new File(filePackageName);
//                if (!packageFile.exists()) {
//                    packageFile.mkdirs();
//                }
//                File docFile = new File( filePackageName+ "/"+className+".java");
//                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
//                template.process(dataMap, out);
                //使用字节流来合并模板,并且将文件输出流直接给压缩,多文件可以使用同一个流,只需要重置
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(arrayOutputStream);
                template.process(dataMap, writer);
                Map<String, byte[]> compressInfo01 = new HashMap<>();
                //将流转为字节数组,方便压缩输出
                byte[] bytes01 = arrayOutputStream.toByteArray();
                compressInfo01.put(filePackageName+ "/"+className+".java", bytes01);
                compressInfoList.add(compressInfo01);
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
        return compressInfoList;
    }

}
