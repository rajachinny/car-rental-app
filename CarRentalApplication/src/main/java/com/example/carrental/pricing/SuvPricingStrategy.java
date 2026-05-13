package com.example.carrental.pricing;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entity.VehicleCategory;
import org.springframework.stereotype.Component;

@Component
public class SuvPricingStrategy implements PricingStrategy {

    @Override
    public VehicleCategory getCategory() {
        return VehicleCategory.SUV;
    }

    @Override
    public double calculatePrice(BookingRequest request) {

        double baseAmount =
                request.getNumberOfDays() * 15;

        double mileageCost =
                request.getDailyMileage()
                        * request.getNumberOfDays()
                        * 0.50;

        return baseAmount + mileageCost;
    }
}