package com.gcd.w40k.battlescribe.profile;

import org.w3c.dom.Node;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public abstract class CatalogProfile {
    private static final String CHARACTERISTIC_XPATH = ".//characteristics/characteristic[@name=\"%s\"]";

    String name;
    String pointCost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPointCost() {
        return pointCost;
    }

    public void setPointCost(String pointCost) {
        this.pointCost = pointCost;
    }

    protected static String getCharacteristicValue(String key, Node profile) throws XPathExpressionException {
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();

        String expression = String.format(CHARACTERISTIC_XPATH, key);
        return (String) xPath.evaluate(expression, profile, XPathConstants.STRING);
    }

    protected static String getPointCost(String name, Node profile) throws XPathExpressionException {
        try {
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xPath = xPathFactory.newXPath();

            String expression = String.format("//selectionEntry[@name=\"%s\"]//costs/cost[@name=\"pts\"]", name);
            Node costNode = (Node) xPath.evaluate(expression, profile, XPathConstants.NODE);
            String pointCost = costNode.getAttributes().getNamedItem("value").getNodeValue();
            return pointCost;
        } catch (NullPointerException ex) {
            System.out.println(String.format("Inconsistency here : %s", name));
            return "0.0";
        }
    }
}
