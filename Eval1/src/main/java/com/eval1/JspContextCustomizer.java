package com.eval1;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.JspConfigDescriptorImpl;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroup;
import org.apache.tomcat.util.descriptor.web.JspPropertyGroupDescriptorImpl;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class JspContextCustomizer implements TomcatContextCustomizer {
    @Override
    public void customize(Context context) {
        JspPropertyGroup group = new JspPropertyGroup();
        group.addUrlPattern("/*");
        group.setPageEncoding("UTF-8");
        context.setJspConfigDescriptor(new JspConfigDescriptorImpl(
                Collections.singletonList(new JspPropertyGroupDescriptorImpl(group)),
                Collections.emptyList()));
    }
}