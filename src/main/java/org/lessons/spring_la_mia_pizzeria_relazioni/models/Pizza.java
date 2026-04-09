package org.lessons.spring_la_mia_pizzeria_relazioni.models;

import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "The name must not be empty")
    @Size(min = 2, max = 50)
    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @URL
    private String photoUrl;

    @NotNull(message = "Price cannot be NULL")
    @Positive(message = "Price cannot be negative or zero")
    @Column(nullable = false)
    private Integer price;

    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.REMOVE })
    private List<Discount> discounts;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return this.bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Discount> getDiscounts() {
        return this.discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", bio='" + getBio() + "'" +
                ", photoUrl='" + getPhotoUrl() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }

}
