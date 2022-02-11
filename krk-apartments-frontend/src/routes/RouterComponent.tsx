import React from "react";
import { Routes, Route } from "react-router-dom";
import { APARTMENTS_LIST, PAYMENT_CREATE } from "./paths";
import ApartmentList from "../view/apartaments/ApartmentList";
import PaymentCreate from "../components/PaymentCreate";


export default function RouterComponent() {
  return (
    <Routes>

      <Route path={PAYMENT_CREATE} element={<PaymentCreate />} />
      <Route path={APARTMENTS_LIST} element={<ApartmentList />} />
    </Routes>
  );
}
