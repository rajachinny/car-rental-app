package com.example.carrental.dto;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class BookingRequest {

    @Min(1)
    private int numberOfDays;

    @Min(0)
    private int dailyMileage;

    @Min(0)
    private int licenseYears;
}