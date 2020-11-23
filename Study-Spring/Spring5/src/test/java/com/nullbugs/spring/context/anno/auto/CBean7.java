package com.nullbugs.spring.context.anno.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


public class CBean7 {

    @Autowired
    private ResourceLoader loader;

    @Value("classpath:jdbc2.properties")
    private Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public ResourceLoader getLoader() {
        return loader;
    }

    public void setLoader(ResourceLoader loader) {
        this.loader = loader;
    }
}
