package com.example.carrental.service.impl;

import com.example.carrental.dto.*;
import com.example.carrental.entity.Reservation;
import com.example.carrental.entity.VehicleCategory;
import com.example.carrental.exception.ReservationNotFoundException;
import com.example.carrental.pricing.PricingStrategy;
import com.example.carrental.repository.ReservationRepository;
import com.example.carrental.service.ReservationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl
        implements ReservationService {

    private final ReservationRepository repository;

    private final List<PricingStrategy> pricingStrategies;

    @Override
    public ReservationResponse reserveCar(ReservationRequest request) {

        PricingStrategy strategy =getStrategy(request.getVehicleCategory());

        BookingRequest bookingRequest =
                new BookingRequest();

        bookingRequest.setNumberOfDays(
                request.getNumberOfDays());

        bookingRequest.setDailyMileage(
                request.getDailyMileage());

        bookingRequest.setLicenseYears(
                request.getLicenseYears());

        double totalAmount =
                strategy.calculatePrice(
                        bookingRequest);

        Reservation reservation =
                Reservation.builder()
                        .customerName(
                                request.getCustomerName())
                        .vehicleCategory(
                                request.getVehicleCategory())
                        .numberOfDays(
                                request.getNumberOfDays())
                        .dailyMileage(
                                request.getDailyMileage())
                        .licenseYears(
                                request.getLicenseYears())
                        .totalAmount(totalAmount)
                        .build();

        Reservation saved =
                repository.save(reservation);

        log.info("Reservation created {}",
                saved.getReservationId());

        return ReservationResponse.builder()
                .reservationId(
                        saved.getReservationId())
                .customerName(
                        saved.getCustomerName())
                .vehicleCategory(
                        saved.getVehicleCategory())
                .totalAmount(
                        saved.getTotalAmount())
                .build();
    }

    @Override
    public ReservationResponse modifyReservation(
            Long id,
            ReservationRequest request) {

        Reservation reservation =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ReservationNotFoundException(
                                        "Reservation not found"));

        PricingStrategy strategy =
                getStrategy(
                        request.getVehicleCategory());

        BookingRequest bookingRequest =
                new BookingRequest();

        bookingRequest.setNumberOfDays(
                request.getNumberOfDays());

        bookingRequest.setDailyMileage(
                request.getDailyMileage());

        bookingRequest.setLicenseYears(
                request.getLicenseYears());

        double totalAmount =
                strategy.calculatePrice(
                        bookingRequest);

        reservation.setCustomerName(
                request.getCustomerName());

        reservation.setVehicleCategory(
                request.getVehicleCategory());

        reservation.setNumberOfDays(
                request.getNumberOfDays());

        reservation.setDailyMileage(
                request.getDailyMileage());

        reservation.setLicenseYears(
                request.getLicenseYears());

        reservation.setTotalAmount(
                totalAmount);

        Reservation updated =
                repository.save(reservation);

        return ReservationResponse.builder()
                .reservationId(
                        updated.getReservationId())
                .customerName(
                        updated.getCustomerName())
                .vehicleCategory(
                        updated.getVehicleCategory())
                .totalAmount(
                        updated.getTotalAmount())
                .build();
    }

    @Override
    public void cancelReservation(Long id) {

        Reservation reservation =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ReservationNotFoundException(
                                        "Reservation not found"));

        repository.delete(reservation);

        log.info("Reservation deleted {}", id);
    }

    @Override
    public List<VehicleOptionResponse> getOptions(
            BookingRequest request) {

        return pricingStrategies.stream()
                .map(strategy ->
                        VehicleOptionResponse.builder()
                                .vehicleCategory(
                                        strategy.getCategory())
                                .totalAmount(
                                        strategy.calculatePrice(
                                                request))
                                .build())
                .sorted(Comparator.comparing(
                        VehicleOptionResponse::getTotalAmount))
                .toList();
    }

    private PricingStrategy getStrategy(
            VehicleCategory category) {

        return pricingStrategies.stream()
                .filter(strategy ->
                        strategy.getCategory() == category)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid category"));
    }
}