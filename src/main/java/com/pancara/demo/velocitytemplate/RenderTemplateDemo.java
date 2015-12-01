package com.pancara.demo.velocitytemplate;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by pancara on 12/1/15.
 */

public class RenderTemplateDemo {
    public static void main(String[] args) throws IOException {
        VelocityEngine engine = new VelocityEngine();

        engine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        engine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        engine.init();
        Template template = engine.getTemplate("template/template.vm");

        ByteOutputStream os = new ByteOutputStream();
        Writer writer = new OutputStreamWriter(os);
        VelocityContext context = createContext();
        template.merge(context, writer);

        writer.flush();

        String text = new String(os.getBytes());
        System.out.println(text);

        writer.close();
    }

    private static VelocityContext createContext() {
        VelocityContext context = new VelocityContext();
        context.put("name", "Badu");
        context.put("address", "Jakarta");

        List list = new LinkedList();
        for (int i = 0; i < 10; i++) {
            list.add("Data " + i);
        }

        context.put("list", list);
        return context;
    }
}
