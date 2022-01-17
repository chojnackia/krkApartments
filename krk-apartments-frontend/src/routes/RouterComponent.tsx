import React from "react";
import { Routes, Route } from "react-router-dom";
import { APARTAMENTS_LIST, PAYMENT_CREATE } from "./paths";
import ApartmentList from "../components/ApartmentList";
import PaymentCreate from "../components/PaymentCreate";


export default function RouterComponent() {
  return (
    <Routes>
      {/* <Route path={APARTAMENTS_LIST} element={<ApartmentList />} /> */}
      <Route path={PAYMENT_CREATE} element={<PaymentCreate />} />
      <Route path={APARTAMENTS_LIST} element={<ApartmentList />} />
    </Routes>
  );
}
