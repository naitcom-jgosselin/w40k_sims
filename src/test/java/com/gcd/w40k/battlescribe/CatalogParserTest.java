package com.gcd.w40k.battlescribe;

import com.gcd.w40k.battlescribe.profile.CatalogModelProfile;
import com.gcd.w40k.battlescribe.profile.CatalogWeaponProfile;
import com.gcd.w40k.utils.ResourceGrabber;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CatalogParserTest {
    @Test
    public void nominalCatalogParsing() throws URISyntaxException, IOException, SAXException, ParserConfigurationException, XPathExpressionException {
        File mechanicusCatalog = ResourceGrabber.getResourceAsFile("battlescribe/catalogs/Imperium - Adeptus Mechanicus.cat");
        CatalogParser parser = new CatalogParser(mechanicusCatalog.toPath());

        List<CatalogModelProfile> models = parser.extractModels();
        List<CatalogWeaponProfile> weapons = parser.extractWeapons();

        assert models.size() == 27;
        assert weapons.size() == 69;
    }
}
