package com.gcd.w40k.battlescribe.profile;

import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpressionException;

public class CatalogModelProfile extends CatalogProfile {

    private static final String MOVE_SPEED = "M";
    private static final String WEAPON_SKILL = "WS";
    private static final String BALLISTIC_SKILL = "BS";
    private static final String STRENGTH = "S";
    private static final String TOUGHNESS = "T";
    private static final String WOUNDS = "W";
    private static final String ATTACKS = "A";
    private static final String LEADERSHIP = "Ld";
    private static final String SAVE = "Save";

    String moveSpeed;
    String weaponSkill;
    String ballisticSkill;
    String strength;
    String toughness;
    String wounds;
    String attacks;
    String leadership;
    String save;

    private CatalogModelProfile() {
    }

    public String getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(String moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public String getWeaponSkill() {
        return weaponSkill;
    }

    public void setWeaponSkill(String weaponSkill) {
        this.weaponSkill = weaponSkill;
    }

    public String getBallisticSkill() {
        return ballisticSkill;
    }

    public void setBallisticSkill(String ballisticSkill) {
        this.ballisticSkill = ballisticSkill;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getToughness() {
        return toughness;
    }

    public void setToughness(String toughness) {
        this.toughness = toughness;
    }

    public String getWounds() {
        return wounds;
    }

    public void setWounds(String wounds) {
        this.wounds = wounds;
    }

    public String getAttacks() {
        return attacks;
    }

    public void setAttacks(String attacks) {
        this.attacks = attacks;
    }

    public String getLeadership() {
        return leadership;
    }

    public void setLeadership(String leadership) {
        this.leadership = leadership;
    }

    public String getSave() {
        return save;
    }

    public void setSave(String save) {
        this.save = save;
    }

    public static CatalogModelProfile buildFromNode(Node profile) throws XPathExpressionException {
        CatalogModelProfile catalogModelProfile = new CatalogModelProfile();

        String name = profile.getAttributes().getNamedItem("name").getNodeValue();
        catalogModelProfile.setName(name);

        String movementSpeed = getCharacteristicValue(MOVE_SPEED, profile);
        catalogModelProfile.setMoveSpeed(movementSpeed);

        String weaponSkill = getCharacteristicValue(WEAPON_SKILL, profile).replace("+", "");
        catalogModelProfile.setWeaponSkill(weaponSkill);

        String ballisticSkill = getCharacteristicValue(BALLISTIC_SKILL, profile).replace("+", "");
        catalogModelProfile.setBallisticSkill(ballisticSkill);

        String strength = getCharacteristicValue(STRENGTH, profile);
        catalogModelProfile.setStrength(strength);

        String toughness = getCharacteristicValue(TOUGHNESS, profile);
        catalogModelProfile.setToughness(toughness);

        String wounds = getCharacteristicValue(WOUNDS, profile);
        catalogModelProfile.setWounds(wounds);

        String attacks = getCharacteristicValue(ATTACKS, profile);
        catalogModelProfile.setAttacks(attacks);

        String leadership = getCharacteristicValue(LEADERSHIP, profile);
        catalogModelProfile.setLeadership(leadership);

        String save = getCharacteristicValue(SAVE, profile).replace("+", "");
        catalogModelProfile.setSave(save);

        return catalogModelProfile;
    }
}
