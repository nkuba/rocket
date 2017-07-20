package com.jnowakowski.rocket.cookbook.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * @author Jakub Nowakowski <jakub.nowakowski@amartus.com>
 */
@Document(collection = "recipes")
public class Recipe {
    @Id
    private ObjectId id;

    @Version
    private Long version;

    @Indexed(unique = true)
    private String name;

    private List<Meal> meal;
    private Map<String, String> ingredients;
    private String description;

    public Long getVersion() {
        return version;
    }

    public List<Meal> getMeal() {
        return meal;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    private String link;

    //TODO Take care of this
    // @Indexed
    // private Date createDate = new Date();
    // private Date updateDate = createDate;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append("id", id).append("version", version).append("name", name)
                                                                          // .append("meal", meal)
                                                                          // .append("ingredients", ingredients)
                                                                          .append("description", description).append("link", link)
                                                                          // .append("createDate", createDate)
                                                                          // .append("updateDate", updateDate)
                                                                          .toString();
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }
}
