package com.example.carrental.dto;

import com.example.carrental.entity.VehicleCategory;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationResponse {

    private Long reservationId;

    private String customerName;

    private VehicleCategory vehicleCategory;

    private double totalAmount;
}