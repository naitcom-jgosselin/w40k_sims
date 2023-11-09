package com.gcd.w40k.utils;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class ResourceGrabber {

    private static final String MODEL_CLASS_TEMPLATE = "templates/model.java.template";

    public static File getResourceAsFile(String path) throws URISyntaxException {
        return Paths.get(ResourceGrabber.class.getClassLoader().getResource(path).toURI()).toFile();
    }

    public static File getModelClassTemplate() throws URISyntaxException {
        return getResourceAsFile(MODEL_CLASS_TEMPLATE);
    }
}
