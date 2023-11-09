package com.gcd.w40k.battlescribe;

import com.gcd.w40k.battlescribe.profile.CatalogModelProfile;
import com.gcd.w40k.battlescribe.profile.CatalogWeaponProfile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CatalogParser {
    Path catalog;

    public CatalogParser(Path catalog) {
        this.catalog = catalog;
    }

    public List<CatalogModelProfile> extractModels() throws XPathExpressionException {
        List<CatalogModelProfile> models = new ArrayList<>();
        Element parsedCatalog = initDocumentElement();
        NodeList unitProfilesNodes = getUnitProfileNodes(parsedCatalog);

        for(int i = 0; i<unitProfilesNodes.getLength(); i++) {
            CatalogModelProfile profile = CatalogModelProfile.buildFromNode(unitProfilesNodes.item(i));
            models.add(profile);
        }
        return models;
    }

    public List<CatalogWeaponProfile> extractWeapons() throws XPathExpressionException {
        List<CatalogWeaponProfile> weapons = new ArrayList<>();
        Element parsedCatalog = initDocumentElement();
        NodeList unitProfilesNodes = getWeaponProfileNodes(parsedCatalog);

        for(int i = 0; i<unitProfilesNodes.getLength(); i++) {
            CatalogWeaponProfile profile = CatalogWeaponProfile.buildFromNode(unitProfilesNodes.item(i));
            weapons.add(profile);
        }
        return weapons;
    }

    private Element initDocumentElement() {
        try {
            final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            final Document document = documentBuilder.parse(catalog.toFile());
            return document.getDocumentElement();
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }

    private NodeList getUnitProfileNodes(Element element) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        String expression = "//profile[@typeName=\"Unit\"]";

        return (NodeList) xPath.evaluate(expression, element, XPathConstants.NODESET);
    }

    private NodeList getWeaponProfileNodes(Element element) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        String expression = "//profile[@typeName=\"Weapon\"]";

        return (NodeList) xPath.evaluate(expression, element, XPathConstants.NODESET);
    }
}
