package com.jnowakowski.rocket.cookbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "recipes")
public class Recipe {
    private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";
    @Id
    private ObjectId id;
    @Version
    private Long version;
    @Indexed(unique = true)
    private String name;
    @Field
    private List<Meal> meal;
    @Field
    private Map<String, String> ingredients;
    @Field
    private String description;
    @Field
    private String link;
    @Indexed
    @DateTimeFormat(pattern = DATE_FORMAT)
    private Date createDate = new Date();
    @DateTimeFormat(pattern = DATE_FORMAT)
    private Date updateDate;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<Meal> getMeal() {
        return meal;
    }

    public void setMeal(List<Meal> meal) {
        this.meal = meal;
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

    public String getId() {
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getUpdateDate() {
        return updateDate;
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
}
