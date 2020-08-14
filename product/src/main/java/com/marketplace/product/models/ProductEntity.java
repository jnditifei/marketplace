package com.marketplace.product.models;


import com.marketplace.product.models.enumerations.categoryEnum;
import com.marketplace.product.models.enumerations.conditionEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
;
import java.util.List;
import java.util.Objects;

@Document(collection = "product")
public class ProductEntity {

    @Id
    private String id;

    private String title;

    private String description;

    protected conditionEnum condition;

    private long price;

    private String model;

    public String brand;

    protected categoryEnum category;

    private List<String> image;

    public ProductEntity() {
    }

    public ProductEntity(String title, conditionEnum condition, long price, String model, String brand, categoryEnum category) {
        this.title = title;
        this.condition = condition;
        this.price = price;
        this.model = model;
        this.brand = brand;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) { this.brand = brand;
    }

    public conditionEnum getCondition() {
        return condition;
    }

    public void setCondition(conditionEnum condition) {
        this.condition = condition;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public categoryEnum getCategory() {
        return category;
    }

    public void setCategory(categoryEnum category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id &&
                price == that.price &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                condition == that.condition &&
                Objects.equals(model, that.model) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, condition, price, model, brand, image);
    }
}
