import React, { useState } from "react";
import axios from "axios";

import {
  FaCar,
  FaUser,
  FaRoad,
  FaCalendarAlt,
  FaIdCard
} from "react-icons/fa";

function App() {

  const [form, setForm] = useState({
    customerName: "",
    vehicleCategory: "SEDAN",
    numberOfDays: "",
    dailyMileage: "",
    licenseYears: ""
  });

  const [options, setOptions] = useState([]);

  const [reservation, setReservation] = useState(null);

  const [error, setError] = useState("");

  const [loading, setLoading] = useState(false);

  // Handle Input Change

  const handleChange = (e) => {

    setForm({
      ...form,
      [e.target.name]: e.target.value
    });

    setError("");
  };

  // Validation

  const validateForm = () => {

    // Customer Name Empty

    if (!form.customerName.trim()) {

      setError(
        "Customer name is required"
      );

      return false;
    }

    // Only Alphabets

    const nameRegex = /^[A-Za-z ]+$/;

    if (
      !nameRegex.test(form.customerName)
    ) {

      setError(
        "Customer name should contain only alphabets"
      );

      return false;
    }

    // Number Of Days

    if (
      Number(form.numberOfDays) < 1
    ) {

      setError(
        "Number of days must be at least 1"
      );

      return false;
    }

    // Daily Mileage

    if (
      Number(form.dailyMileage) < 0
    ) {

      setError(
        "Daily mileage cannot be negative"
      );

      return false;
    }

    // License Years

    if (
      Number(form.licenseYears) < 0
    ) {

      setError(
        "License years cannot be negative"
      );

      return false;
    }

    return true;
  };

  // Get Vehicle Options

  const getOptions = async () => {

    if (!validateForm()) {
      return;
    }

    try {

      setLoading(true);

      const response = await axios.post(
        "http://localhost:8080/api/reservations/options",
        {
          numberOfDays:
            Number(form.numberOfDays),

          dailyMileage:
            Number(form.dailyMileage),

          licenseYears:
            Number(form.licenseYears)
        }
      );

      setOptions(response.data);

      setReservation(null);

      setError("");

    } catch (err) {

      console.log(err);

      setError(
        "Failed to fetch vehicle options"
      );

    } finally {

      setLoading(false);
    }
  };

  // Reserve Car

  const reserveCar = async () => {

    if (!validateForm()) {
      return;
    }

    try {

      setLoading(true);

      const response = await axios.post(
        "http://localhost:8080/api/reservations",
        {
          customerName:
            form.customerName,

          vehicleCategory:
            form.vehicleCategory,

          numberOfDays:
            Number(form.numberOfDays),

          dailyMileage:
            Number(form.dailyMileage),

          licenseYears:
            Number(form.licenseYears)
        }
      );

      setReservation(response.data);

      setError("");

    } catch (err) {

      console.log(err);

      setError(
        "Reservation failed"
      );

    } finally {

      setLoading(false);
    }
  };

  return (

    <div
      className="min-vh-100"
      style={{
        backgroundImage:
          "linear-gradient(rgba(0,0,0,0.75), rgba(0,0,0,0.75)), url('https://images.unsplash.com/photo-1503376780353-7e6692767b70?q=80&w=2070')",

        backgroundSize: "cover",

        backgroundPosition: "center",

        backgroundRepeat: "no-repeat"
      }}
    >

      {/* Navbar */}

      <nav className="navbar navbar-dark bg-dark shadow-sm px-4">

        <span className="navbar-brand fw-bold fs-3">

          🚗 CarRental Pro

        </span>

      </nav>

      <div className="container py-5">

        <div className="row justify-content-center">

          <div className="col-lg-5 col-md-7">

            {/* Hero */}

            <div className="text-center text-white mb-4">

              <FaCar
                size={70}
                className="mb-3"
              />

              <h1 className="fw-bold display-3">

                Car Rental

              </h1>

              <p className="fs-4 text-light">

                Fast • Secure • Affordable

              </p>

            </div>

            {/* Main Card */}

            <div
              className="shadow-lg rounded-4 p-4"
              style={{
                background:
                  "rgba(255,255,255,0.15)",

                backdropFilter:
                  "blur(12px)",

                border:
                  "1px solid rgba(255,255,255,0.2)"
              }}
            >

              {
                error && (

                  <div className="alert alert-danger">

                    {error}

                  </div>
                )
              }

              {/* Customer Name */}

              <div className="mb-3">

                <label className="form-label fw-bold text-white">

                  <FaUser className="me-2" />

                  Customer Name

                </label>

                <input
                  type="text"
                  name="customerName"
                  className="form-control form-control-lg rounded-3"
                  value={form.customerName}

                  onChange={(e) => {

                    const value =
                      e.target.value;

                    // Allow only alphabets and spaces

                    if (
                      /^[A-Za-z ]*$/.test(value)
                    ) {

                      handleChange(e);
                    }
                  }}
                />

              </div>

              {/* Vehicle Category */}

              <div className="mb-3">

                <label className="form-label fw-bold text-white">

                  <FaCar className="me-2" />

                  Vehicle Category

                </label>

                <select
                  name="vehicleCategory"
                  className="form-select form-select-lg rounded-3"
                  value={form.vehicleCategory}
                  onChange={handleChange}
                >

                  <option value="SEDAN">
                    SEDAN
                  </option>

                  <option value="SUV">
                    SUV
                  </option>

                  <option value="VAN">
                    VAN
                  </option>

                  <option value="PICKUP_TRUCK">
                    PICKUP TRUCK
                  </option>

                </select>

              </div>

              {/* Number Of Days */}

              <div className="mb-3">

                <label className="form-label fw-bold text-white">

                  <FaCalendarAlt className="me-2" />

                  Number Of Days

                </label>

                <input
                  type="number"
                  min="1"
                  name="numberOfDays"
                  className="form-control form-control-lg rounded-3"
                  value={form.numberOfDays}
                  onChange={handleChange}
                />

              </div>

              {/* Daily Mileage */}

              <div className="mb-3">

                <label className="form-label fw-bold text-white">

                  <FaRoad className="me-2" />

                  Daily Mileage

                </label>

                <input
                  type="number"
                  min="0"
                  name="dailyMileage"
                  className="form-control form-control-lg rounded-3"
                  value={form.dailyMileage}
                  onChange={handleChange}
                />

              </div>

              {/* License Years */}

              <div className="mb-4">

                <label className="form-label fw-bold text-white">

                  <FaIdCard className="me-2" />

                  License Years

                </label>

                <input
                  type="number"
                  min="0"
                  name="licenseYears"
                  className="form-control form-control-lg rounded-3"
                  value={form.licenseYears}
                  onChange={handleChange}
                />

              </div>

              {/* Buttons */}

              <div className="d-grid gap-2 d-md-flex">

                <button
                  className="btn btn-primary btn-lg px-4"
                  onClick={getOptions}
                  disabled={loading}
                >

                  {
                    loading
                      ? "Loading..."
                      : "Get Vehicle Options"
                  }

                </button>

                <button
                  className="btn btn-success btn-lg px-4"
                  onClick={reserveCar}
                  disabled={loading}
                >

                  {
                    loading
                      ? "Processing..."
                      : "Reserve Car"
                  }

                </button>

              </div>

            </div>

            {/* Vehicle Options */}

            <div
              className="mt-4 rounded-4 p-4 shadow-lg"
              style={{
                background:
                  "rgba(255,255,255,0.15)",

                backdropFilter:
                  "blur(12px)",

                border:
                  "1px solid rgba(255,255,255,0.2)"
              }}
            >

              <h3 className="fw-bold text-white mb-4">

                Available Vehicle Options

              </h3>

              {
                options.length === 0 ? (

                  <div className="alert alert-secondary mb-0">

                    No vehicle options available

                  </div>

                ) : (

                  <div className="row">

                    {
                      options.map((option, index) => (

                        <div
                          key={index}
                          className="col-12 mb-3"
                        >

                          <div className="card shadow border-0 rounded-4">

                            <div className="card-body d-flex justify-content-between align-items-center">

                              <div>

                                <h5 className="fw-bold">

                                  🚘 {option.vehicleCategory}

                                </h5>

                              </div>

                              <span className="badge bg-success fs-6 px-3 py-2">

                                ${option.totalAmount}

                              </span>

                            </div>

                          </div>

                        </div>
                      ))
                    }

                  </div>
                )
              }

            </div>

            {/* Reservation Success */}

            {
              reservation && (

                <div className="alert alert-success mt-4 shadow-lg rounded-4 p-4">

                  <h3 className="fw-bold">

                    Reservation Successful 🎉

                  </h3>

                  <hr />

                  <p>

                    <strong>
                      Reservation Id:
                    </strong>

                    {" "}

                    {reservation.reservationId}

                  </p>

                  <p>

                    <strong>
                      Customer Name:
                    </strong>

                    {" "}

                    {reservation.customerName}

                  </p>

                  <p>

                    <strong>
                      Vehicle Category:
                    </strong>

                    {" "}

                    {reservation.vehicleCategory}

                  </p>

                  <p>

                    <strong>
                      Total Amount:
                    </strong>

                    {" "}

                    ${reservation.totalAmount}

                  </p>

                </div>
              )
            }

            {/* Footer */}

            <div className="text-center text-light mt-5">

              © 2026 CarRental Pro • All Rights Reserved

            </div>

          </div>

        </div>

      </div>

    </div>
  );
}

export default App;