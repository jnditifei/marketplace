package com.marketplace.product.models.enumerations;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;

public enum conditionEnum {

    NEUF("Neuf"),
    NEUFRECONDITIONNE("Reconditionné: Comme neuf"),
    RECONDITONNE("Reconditionné: Bon état"),
    OCCASION("Occasion");

    private String name;

    conditionEnum(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the name
     */
    @JsonValue
    public String getName() {
        return name;
    }

    @JsonCreator
    public static conditionEnum fromValue(final JsonNode jsonNode) {

        for (conditionEnum type : conditionEnum.values()) {
            if (type.name.equals(jsonNode.get("name").asText())) {
                return type;
            }
        }
        return null;
    }
}
