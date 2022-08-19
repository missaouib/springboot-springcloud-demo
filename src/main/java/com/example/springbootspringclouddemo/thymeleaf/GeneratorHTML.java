package com.example.springbootspringclouddemo.thymeleaf;

import com.netflix.discovery.converters.Auto;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.beans.factory.annotation.Autowired;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

public class GeneratorHTML {

    @Autowired
    private TemplateEngine templateEngine;

    public void createHTML(Long spuId) {
        //org.thymeleaf.context.Context;
        Context context = new Context();
        //加载数据
        context.setVariables(new HashMap<>());
        //输出流
        File dest = new File("/Users/upload", spuId + ".html");
        try (PrintWriter writer = new PrintWriter(dest, "UTF-8")) {
            //生成HTML,item是模板名称
            templateEngine.process("item", context, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
