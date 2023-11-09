package com.gcd.w40k.scripts;

import com.gcd.w40k.battlescribe.CatalogParser;
import com.gcd.w40k.battlescribe.ModelGenerator;
import com.gcd.w40k.battlescribe.profile.CatalogModelProfile;
import com.gcd.w40k.utils.ResourceGrabber;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

public class GenerateModelsFromCatalog {
    public static void main(String args[]) throws URISyntaxException, XPathExpressionException {
        ModelGenerator generator = new ModelGenerator();
        File mechanicusCatalog = ResourceGrabber.getResourceAsFile("battlescribe/catalogs/Imperium - Adeptus Mechanicus.cat");
        CatalogParser parser = new CatalogParser(mechanicusCatalog.toPath());

        for(CatalogModelProfile profile : parser.extractModels()) {
            generator.generateModel("admech", profile);
        }


    }
}
