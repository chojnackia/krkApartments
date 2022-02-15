import React from "react";
import { Routes, Route } from "react-router-dom";
import { APARTMENTS_LIST, APARTMENT_DETAILS, PAYMENT_CREATE } from "./paths";
import ApartmentList from "../view/apartaments/ApartmentList";
import PaymentCreate from "../components/PaymentCreate";
import { ApartmentDetails } from "../components/ApartmentDetails";


export default function RouterComponent() {
  return (
    <Routes>
      <Route path={PAYMENT_CREATE} element={<PaymentCreate />} />
      <Route path={APARTMENTS_LIST} element={<ApartmentList />} />
      <Route path={APARTMENT_DETAILS} element={<ApartmentDetails />} />
    </Routes>
  );
}
