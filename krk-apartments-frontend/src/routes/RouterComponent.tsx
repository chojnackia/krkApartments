import React from "react";
import { Routes, Route } from "react-router-dom";
import { PAYMENT_CREATE } from "./paths";
import PaymentCreate from "../components/PaymentCreate";


export default function RouterComponent() {
  return (
    <Routes>
      {/* <Route path={APARTAMENTS_LIST} element={<ApartmentList />} /> */}
      <Route path={PAYMENT_CREATE} element={<PaymentCreate />} />
    </Routes>
  );
}
