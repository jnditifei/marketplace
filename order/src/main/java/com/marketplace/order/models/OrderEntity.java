package com.marketplace.order.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Date;
import java.util.List;

@NodeEntity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long OrderId;

    private long price;

    private Date orderDate;

    private Date shipmentDate;

    private String status;

    private long buyerId;

    private List<String> productId;

    private long addressId;

    private long sellerId;

    @Relationship(type = "ACTED_IN", direction = Relationship.OUTGOING)
    private List<CommentEntity> comments;

}
