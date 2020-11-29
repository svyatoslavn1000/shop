package com.geekbrains.decembermarket.entities;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "trucks")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "gos_number")
    @NotNull
    private String gosNumber;

    @Column(name = "VIN")
    @NotNull
    private String vin;

    @Column(name = "sts_number")
    @NotNull
    private String stsNumber;

    @Column(name = "brand_of_truck")
    @NotNull
    private String brandOfTruck;

    @Column(name = "cargo_capacity")
    @NotNull
    private Double cargoCapacity;

    @Column(name = "year_of_production")
    @NotNull
    private String yearOfProduction;

    @Column(name = "is_used")
    boolean isUsed;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public String getMainInfo() {
        return String.format("%s %s %s", gosNumber, brandOfTruck, yearOfProduction);
    }
}
