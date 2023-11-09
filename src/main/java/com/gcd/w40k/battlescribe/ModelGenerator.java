package com.gcd.w40k.battlescribe;

import com.gcd.w40k.battlescribe.profile.CatalogModelProfile;
import com.gcd.w40k.utils.ResourceGrabber;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ModelGenerator {
    private static final String PACKAGE_KEY = "${package.name}";
    private static final String CLASSNAME_KEY = "${model.classname}";
    private static final String WEAPON_SKILL_KEY = "${model.ws}";
    private static final String BALLISTIC_SKILL_KEY = "${model.bs}";
    private static final String TOUGHNESS_KEY = "${model.toughness}";
    private static final String STRENGTH_KEY = "${model.strength}";
    private static final String HP_KEY = "${model.hp}";
    private static final String SAVE_KEY = "${model.save}";

    public void generateModel(String packageName, CatalogModelProfile profile) {
        try {
            File modelTemplate = ResourceGrabber.getModelClassTemplate();
            String modelClassName = generateClassName(profile);
            String stringTemplate = new String(Files.readAllBytes(modelTemplate.toPath()));
            stringTemplate = stringTemplate.replace(PACKAGE_KEY, packageName)
                    .replace(CLASSNAME_KEY, modelClassName)
                    .replace(WEAPON_SKILL_KEY, profile.getWeaponSkill())
                    .replace(BALLISTIC_SKILL_KEY, profile.getBallisticSkill())
                    .replace(TOUGHNESS_KEY, profile.getToughness())
                    .replace(STRENGTH_KEY, profile.getStrength())
                    .replace(HP_KEY, profile.getWounds())
                    .replace(SAVE_KEY, profile.getSave());

            writeJavaModel(packageName, modelClassName, stringTemplate);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateClassName(CatalogModelProfile profile) {
        String name = profile.getName();
        return name.replace(" ", "").replace("-", "");
    }

    private void writeJavaModel(String packageName, String className, String classContent) throws IOException {
        Path packagePath = Paths.get("src/main/java/com/gcd/w40k/tableunits/generated/" + packageName);

        if(!packagePath.toFile().exists()) {
            Files.createDirectory(packagePath);
        }

        Path classFile = packagePath.resolve(className + ".java");
        if(!classFile.toFile().exists()) {
            Files.createFile(classFile);
        }
        Files.write(classFile, classContent.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    }
}
