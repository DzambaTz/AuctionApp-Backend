package com.example.AuctionApp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name ="roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RolesEnum name;
}
