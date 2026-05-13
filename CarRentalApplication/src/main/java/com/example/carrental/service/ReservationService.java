package com.example.carrental.service;

import com.example.carrental.dto.*;

import java.util.List;

public interface ReservationService {

    ReservationResponse reserveCar(ReservationRequest request);

    ReservationResponse modifyReservation(Long id, ReservationRequest request);

    void cancelReservation(Long id);

    List<VehicleOptionResponse> getOptions( BookingRequest request);
}