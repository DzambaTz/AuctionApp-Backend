package com.example.AuctionApp.models;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Table(name = "items")
@Entity(name ="Item")
@Getter
@Setter
@ToString
@NoArgsConstructor
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
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
    @Column(
            length = 1000
    )
    private String description;
    private Instant start_time;
    private Instant end_time;
    @Type(type = "list-array")
    @Column(
            name = "images",
            columnDefinition = "text[]"
    )
    private List<String> images;

    public Item(User user, String name, String category, String subcategory, Float start_price, String description, Instant start_time, Instant end_time, List<String> images) {
        this.user = user;
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.start_price = start_price;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.images = images;
    }
}
