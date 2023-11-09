package com.gcd.w40k.resolvers.save;

import com.gcd.w40k.resolvers.wound.Wound;

import java.util.Arrays;

public class SaveResolver {
    public static int[] getUnsavedOutcomes(Wound wound, Save save) {
        int apModifier = wound.getApModifier();
        int[] unsavedOn = new int[] {1,2,3,4,5,6};

        if(save.getNormalSave().isPresent()) {
            int modifiedSave = save.getNormalSave().get() - apModifier;
            if(save.getInvulnerableSave().isPresent() && save.getInvulnerableSave().get() <= modifiedSave) {
                return Arrays.copyOfRange(unsavedOn, 0, save.getInvulnerableSave().get() - 1);
            } else {
                return Arrays.copyOfRange(unsavedOn, 0, modifiedSave - 1);
            }
        } else if(save.getInvulnerableSave().isPresent()) {
            return Arrays.copyOfRange(unsavedOn, 0, save.getInvulnerableSave().get() - 1);
        }
        return unsavedOn;
    }
}
