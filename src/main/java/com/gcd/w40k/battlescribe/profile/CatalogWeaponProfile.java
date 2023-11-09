package com.gcd.w40k.battlescribe.profile;

import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;

public class CatalogWeaponProfile extends CatalogProfile {

    private static final String RANGE = "Range";
    private static final String TYPE = "Type";
    private static final String STRENGTH = "S";
    private static final String ARMOR_PENETRATION = "AP";
    private static final String DAMAGE = "D";

    String range;
    String type;
    String strength;
    String armorPenetration;
    String damage;

    private CatalogWeaponProfile() {
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getArmorPenetration() {
        return armorPenetration;
    }

    public void setArmorPenetration(String armorPenetration) {
        this.armorPenetration = armorPenetration;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public static CatalogWeaponProfile buildFromNode(Node profile) throws XPathExpressionException {
        CatalogWeaponProfile catalogWeaponProfile = new CatalogWeaponProfile();

        String name = profile.getAttributes().getNamedItem("name").getNodeValue();
        catalogWeaponProfile.setName(name);

        String range = getCharacteristicValue(RANGE, profile);
        catalogWeaponProfile.setRange(range);

        String type = getCharacteristicValue(TYPE, profile);
        catalogWeaponProfile.setType(type);

        String strength = getCharacteristicValue(STRENGTH, profile);
        catalogWeaponProfile.setStrength(strength);

        String armorPenetration = getCharacteristicValue(ARMOR_PENETRATION, profile);
        catalogWeaponProfile.setArmorPenetration(armorPenetration);

        String damage = getCharacteristicValue(DAMAGE, profile);
        catalogWeaponProfile.setDamage(damage);

        return catalogWeaponProfile;
    }
}
