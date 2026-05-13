package com.example.carrental.pricing;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entity.VehicleCategory;
import org.springframework.stereotype.Component;

@Component
public class SedanPricingStrategy implements PricingStrategy {

    @Override
    public VehicleCategory getCategory() {
        return VehicleCategory.SEDAN;
    }

    @Override
    public double calculatePrice(BookingRequest request) {

        double dailyPrice =
                request.getNumberOfDays() < 10 ? 20 : 15;

        return dailyPrice * request.getNumberOfDays();
    }
}