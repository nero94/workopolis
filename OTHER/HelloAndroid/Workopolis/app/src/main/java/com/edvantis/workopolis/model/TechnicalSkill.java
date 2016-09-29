package com.edvantis.workopolis.model;

/**
 * Created by vasyl.dmytriv on 9/11/2016.
 */
public class TechnicalSkill {

    private int id;

    private String name;

    private TechnicalSkillLevel level;

    private TechSkillCategory category;

    private Importance importance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TechnicalSkillLevel getLevel() {
        return level;
    }

    public void setLevel(TechnicalSkillLevel level) {
        this.level = level;
    }

    public TechSkillCategory getCategory() {
        return category;
    }

    public void setCategory(TechSkillCategory category) {
        this.category = category;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }

    @Override
    public String toString() {
        return  "-\tcategory: " + category +
                ", skill name: " + name +
                ", level: " + level +
                ", importance: " + importance + "\n\n";
    }
}
