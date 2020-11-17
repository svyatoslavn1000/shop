package com.geekbrains.decembermarket.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Data
@NoArgsConstructor
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    @NotNull
    private String surname;

    @Column(name = "driver_card")
    private String driverCard;

    @Column(name = "passport")
    private String passport;

    @Column(name = "citizenship")
    private String citizenship;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    public String getMainInfo() {
        return String.format("%s %s %s", name, surname, driverCard, passport, citizenship, truck);
    }
}