package com.example.carrental.dto;

import com.example.carrental.entity.VehicleCategory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ReservationRequest {

    @NotBlank(message = "Customer name is required")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "Customer name should contain only alphabets" )
    private String customerName;

   @NotNull(message = "Vehicle category is required")
    private VehicleCategory vehicleCategory;

    @Min(value = 1,message = "Days should be greater than 0")
    private int numberOfDays;

    @Min(value = 0,message = "Mileage cannot be negative")
    private int dailyMileage;

    @Min(value = 0,message = "License years cannot be negative")
    private int licenseYears;
}