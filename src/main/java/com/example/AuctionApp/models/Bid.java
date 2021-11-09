package com.example.AuctionApp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "bids")
@Entity(name = "Bid")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private Float amount;

    private Instant dateCreated;

    public Bid(Item item, User user, Float amount, Instant dateCreated) {
        this.item = item;
        this.user = user;
        this.amount = amount;
        this.dateCreated = dateCreated;
    }
}
