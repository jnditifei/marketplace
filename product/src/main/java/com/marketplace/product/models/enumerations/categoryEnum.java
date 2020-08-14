package com.marketplace.product.models.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;

public enum categoryEnum {

    EMPLOI("emploi"),
    AUTOMOBILE("Automobile"),
    IMMOBILIER("Immobilier"),
    MODE("Mode"),
    LOISIRS("Loisirs"),
    MULTIMEDIA("Multimédia"),
    AUTRES("Autres");

    private String name;

    categoryEnum(String name){
        this.name = name;
    }

    /**
     * @return the name
     */
    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static categoryEnum fromValue(final JsonNode jsonNode) {

        for (categoryEnum type : categoryEnum.values()) {
            if (type.name.equals(jsonNode.get("name").asText())) {
                return type;
            }
        }
        return null;
    }

}
