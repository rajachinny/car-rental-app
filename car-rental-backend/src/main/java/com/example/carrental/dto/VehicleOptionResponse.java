package com.example.carrental.dto;

import com.example.carrental.entity.VehicleCategory;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleOptionResponse {

    private VehicleCategory vehicleCategory;

    private double totalAmount;
}