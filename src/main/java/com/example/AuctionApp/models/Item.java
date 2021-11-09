package com.example.AuctionApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "items")
@Entity(name ="Item")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;
    private String category;
    private String subcategory;
    private Float start_price;
    private String description;
    private Instant start_time;
    private Instant end_time;
    private String image;

    public Item(User user, String name, String category, String subcategory, Float start_price, String description, Instant start_time, Instant end_time, String image) {
        this.user = user;
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.start_price = start_price;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.image = image;
    }
}
