package com.marketplace.users.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum SexEnum {

    MAN("man"), WOMAN("woman");

    private String name;

    private SexEnum(String name) {
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
    public static SexEnum fromValue(final JsonNode jsonNode) {

        for (SexEnum type : SexEnum.values()) {
            if (type.name.equals(jsonNode.get("name").asText())) {
                return type;
            }
        }
        return null;
    }

}
