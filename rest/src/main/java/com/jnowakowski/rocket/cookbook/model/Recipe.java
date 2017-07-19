package com.jnowakowski.rocket.cookbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
@Entity("recipes")
public class Recipe {
    @Id
    private ObjectId id;

    @Version
    private Long version;

    @Indexed(options = @IndexOptions(unique = true))
    private String name;
    private List<Meal> meal;
    private Map<String, String> ingredients;
    private String description;
    private String link;

    @Indexed
    private Date createDate;
    private Date updateDate;

    public Recipe(String name, List<Meal> meal, Map<String, String> ingredients, String description, String link) {
        this.id = ObjectId.get();
        this.name = name;
        this.meal = meal;
        this.ingredients = ingredients;
        this.description = description;
        this.link = link;
        this.createDate = new Date();
        this.updateDate = createDate;
    }

    public Recipe() {
    }

    public List<Meal> getMeal() {
        return meal;
    }

    public void setMeal(List<Meal> meal) {
        this.meal = meal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", id)
                                                                          .append("version", version)
                                                                          .append("name", name)
                                                                          .append("meal", meal)
                                                                          .append("ingredients", ingredients)
                                                                          .append("description", description)
                                                                          .append("link", link)
                                                                          .append("createDate", createDate)
                                                                          .append("updateDate", updateDate)
                                                                          .toString();
    }

    public ObjectId getId() {
        return id;
    }
}
