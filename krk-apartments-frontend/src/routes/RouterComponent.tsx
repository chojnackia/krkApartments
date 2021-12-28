import React from "react";
import { Routes, Route } from "react-router-dom";
import { APARTAMENTS_LIST, PAYMENT_CREATE } from "./paths";
import ApartamentsList from "../view/apartaments/ApartamentsList";
import PaymentCreate from "../components/PaymentCreate";

export default function RouterComponent() {
  return (
    <Routes>
      <Route path={APARTAMENTS_LIST} element={<ApartamentsList />} />
      <Route path={PAYMENT_CREATE} element={<PaymentCreate amount={0} currency={""} email={""} phone={""} client={""} description={""} token={""} />} />
    </Routes>
  );
}
