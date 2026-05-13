package com.example.carrental.pricing;

import com.example.carrental.dto.BookingRequest;
import com.example.carrental.entity.VehicleCategory;
import org.springframework.stereotype.Component;

@Component
public class VanPricingStrategy implements PricingStrategy {

    @Override
    public VehicleCategory getCategory() {
        return VehicleCategory.VAN;
    }

    @Override
    public double calculatePrice(BookingRequest request) {

        double baseAmount =
                request.getNumberOfDays() * 22;

        double cleaningFee = baseAmount * 0.10;

        return baseAmount + cleaningFee;
    }
}