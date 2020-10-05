package com.marketplace.order.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.*;

import java.util.Collection;

@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@RelationshipEntity(type = "Belong_to")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Belong {

    @Id
    @GeneratedValue
    private Long id;

    private Collection<String> belongs;

    @StartNode
    private ReviewEntity review;

    @EndNode
    private OrderEntity order;
}
