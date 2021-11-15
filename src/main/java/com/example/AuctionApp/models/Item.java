/**
 * Item class is a model for the items database table.
 *
 * @author Tarik Dzambic
 */

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
    private Float startPrice;
    @Column(
            length = 1000
    )
    private String description;
    private Instant startTime;
    private Instant endTime;
    @Type(type = "list-array")
    @Column(
            name = "images",
            columnDefinition = "text[]"
    )
    private List<String> images;

    public Item(User user, String name, String category, String subcategory, Float startPrice, String description, Instant startTime, Instant endTime, List<String> images) {
        this.user = user;
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.startPrice = startPrice;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.images = images;
    }
}
