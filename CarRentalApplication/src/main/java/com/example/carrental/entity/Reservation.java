package com.example.carrental.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private String customerName;

    @Enumerated(EnumType.STRING)
    private VehicleCategory vehicleCategory;

    private int numberOfDays;

    private int dailyMileage;

    private int licenseYears;

    private double totalAmount;
}