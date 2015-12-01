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
import java.util.HashMap;
import java.util.Map;

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
        template.merge(getContext(), writer);
        writer.flush();

        String text = new String(os.getBytes());
        System.out.println(text);

        writer.close();
    }

    private static VelocityContext getContext() {
        Map<String, Object> valueMap = new HashMap<String, Object>();

        valueMap.put("name", "Badu");
        valueMap.put("address", "Jakarta");

        VelocityContext context = new VelocityContext();
        for (Map.Entry<String, Object> entry : valueMap.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
        return context;
    }
}
