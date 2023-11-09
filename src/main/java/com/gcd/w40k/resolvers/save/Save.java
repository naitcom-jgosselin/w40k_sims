package com.gcd.w40k.resolvers.save;

import java.util.Optional;

public class Save {
    Integer normalSave;
    Integer invulnerableSave;

    private Save() {
    }

    public Optional<Integer> getNormalSave() {
        return Optional.ofNullable(normalSave);
    }

    private void setNormalSave(int normalSave) {
        this.normalSave = normalSave;
    }

    public Optional<Integer> getInvulnerableSave() {
        return Optional.ofNullable(invulnerableSave);
    }

    private void setInvulnerableSave(int invulnerableSave) {
        this.invulnerableSave = invulnerableSave;
    }

    public static SaveBuilder builder() {
        return new SaveBuilder();
    }


    public static class SaveBuilder {
        Save save;

        SaveBuilder() {
            this.save = new Save();
        }

        public SaveBuilder withNormalSave(int normalSave) {
            this.save.setNormalSave(normalSave);
            return this;
        }

        public SaveBuilder withInvulnerableSave(int invulnerableSave) {
            this.save.setInvulnerableSave(invulnerableSave);
            return this;
        }

        public Save build() {
            return save;
        }
    }
}
