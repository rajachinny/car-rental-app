package com.example.carrental.pricing;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entity.VehicleCategory;
import org.springframework.stereotype.Component;

@Component
public class PickupTruckPricingStrategy
        implements PricingStrategy {

    @Override
    public VehicleCategory getCategory() {
        return VehicleCategory.PICKUP_TRUCK;
    }

    @Override
    public double calculatePrice(BookingRequest request) {

        double amount =
                request.getNumberOfDays() * 30;

        if (request.getLicenseYears() < 3) {
            amount += amount * 0.10;
        }

        return amount;
    }
}