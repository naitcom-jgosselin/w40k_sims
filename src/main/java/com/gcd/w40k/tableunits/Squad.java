package com.gcd.w40k.tableunits;

import com.gcd.w40k.dice.Dice;
import com.gcd.w40k.resolvers.wound.Wound;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public abstract class Squad {

    public abstract List<Model> getModels();
    public abstract String getDescription();

    public void setAssignedDice(Dice dice) {
        for(Model model : getLivingMembers()) {
            model.setAssignedDice(dice);
        }
    }

    public List<Model> getLivingMembers() {
        List<Model> livingMembers = new ArrayList<>();
        for(Model model : getModels()) {
            if(model.getCurrentHitPoints() > 0) {
                livingMembers.add(model);
            }
        }
        return livingMembers;
    }

    public void resolveSave(Wound wound) {
        try {
            Model mostWoundedMember = getMostWoundedModel();
            mostWoundedMember.resolveSave(wound);
        } catch (NoLivingModelException e) {
            // swallow exception
        }

    }

    public Model getMostWoundedModel() throws NoLivingModelException {
        Model mostWoundedModel = null;
        for(Model model : getLivingMembers()) {
            if(mostWoundedModel == null || mostWoundedModel.getCurrentHitPoints() > model.getCurrentHitPoints()) {
                mostWoundedModel = model;
            }
        }
        if(mostWoundedModel == null) {
            throw new NoLivingModelException();
        }
        return mostWoundedModel;
    }

    public void shootAt(Squad target, int range) {
        for(Model squadMember : getLivingMembers()) {
            squadMember.resolveRangedPhase(target, range);
        }
    }

    public void restore() {
        for(Model model : getModels()) {
            model.restore();
        }
    }

    public BigDecimal getTotalPointCost() {
        BigDecimal totalPointCost = BigDecimal.ZERO;
        for(Model model : getModels()) {
            BigDecimal modelPointCost = BigDecimal.valueOf(model.getPointCost());
            totalPointCost = totalPointCost.add(modelPointCost);
        }
        return totalPointCost;
    }

    public static Squad create(Class<? extends Model> modelClass, int count, String description) {
        try {
            Constructor modelConstructor = modelClass.getConstructor();
            List<Model> models = new ArrayList<>();

            for(int i = 0; i<count; i++) {
                models.add((Model) modelConstructor.newInstance());
            }

            return new Squad() {
                @Override
                public List<Model> getModels() {
                    return models;
                }

                @Override
                public String getDescription() {
                    return description;
                }
            };
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
