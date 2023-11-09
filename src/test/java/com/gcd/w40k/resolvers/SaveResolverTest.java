package com.gcd.w40k.resolvers;

import com.gcd.w40k.resolvers.save.Save;
import com.gcd.w40k.resolvers.save.SaveResolver;
import com.gcd.w40k.resolvers.wound.Wound;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SaveResolverTest {
    @Test
    @DisplayName("When saving a wound with no AP, the normal save should be used")
    public void testNormalSave() {
        Save save = Save.builder().withNormalSave(3).build();
        int[] unsavedWounds = SaveResolver.getUnsavedOutcomes(Wound.standard(), save);

        assert Arrays.equals(unsavedWounds, new int[] {1,2});
    }

    @Test
    @DisplayName("When saving a wound with -1 AP, the normal save is offset by 1")
    public void testModifiedNormalSave() {
        Save save = Save.builder().withNormalSave(3).build();
        int[] unsavedWounds = SaveResolver.getUnsavedOutcomes(new Wound(-1, 1), save);

        assert Arrays.equals(unsavedWounds, new int[] {1,2,3});
    }

    @Test
    @DisplayName("When the invulnerable save is inferior to the normal save, the normal save is used")
    public void testNormalSaveAboveInvulnerable() {
        Save save = Save.builder().withNormalSave(3)
                .withInvulnerableSave(5)
                .build();

        int[] unsavedWounds = SaveResolver.getUnsavedOutcomes(Wound.standard(), save);

        assert Arrays.equals(unsavedWounds, new int[] {1,2});
    }

    @Test
    @DisplayName("When the invulnerable save is superior to the normal save, the invulnerable save is used")
    public void testInvulnerableSaveAboveNormal() {
        Save save = Save.builder().withNormalSave(2)
                .withInvulnerableSave(4)
                .build();

        int[] unsavedWounds = SaveResolver.getUnsavedOutcomes(new Wound(-3, 2), save);

        assert Arrays.equals(unsavedWounds, new int[] {1,2,3});

    }
}
