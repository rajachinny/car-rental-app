package com.example.carrental.pricing;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entity.VehicleCategory;

public interface PricingStrategy {

    VehicleCategory getCategory();

    double calculatePrice(BookingRequest request);
}