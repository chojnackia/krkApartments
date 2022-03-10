import { useForm, Controller, SubmitHandler } from "react-hook-form";
import Input from "@material-ui/core/Input";
import DatePicker from "react-datepicker";
import { useParams } from "react-router-dom";
import { IBooking } from "../interfaces/IBooking";

import "react-datepicker/dist/react-datepicker.css";
import { useEffect, useState } from "react";
import axios from "axios";

interface PaymentInputs {
  amount: number;
  currency: string;
  email: string;
  phone: string;
  client: string;
  description: string;
}

export function PaymentCreate() {
  const { control, handleSubmit } = useForm<PaymentInputs>();
  const [bookings, setBookings] = useState<any[]>([]);
  const [checkInDate, setCheckInDate] = useState(new Date());
  const { id } = useParams();
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());

  const onChange = (dates: [Date, Date]) => {
    const [start, end] = dates;
    setStartDate(start);
    setEndDate(end);
  };
  const occupiedDatesTemp: Date[] = [];

  useEffect(() => {
    axios
      .get(`http://localhost:8080/bookings/apartments/${id}`)
      .then(({ data }) => {
        console.log(data);
        data.map((data: IBooking) => {
          let start = new Date(data.checkInDate);
          let end = new Date(data.checkOutDate);
          let date = new Date(start);
          while (date <= end) {
            occupiedDatesTemp.push(new Date(date));
            date.setDate(date.getDate().valueOf() + 1);
          }
        });
      });
  }, [occupiedDatesTemp]);

  const onSubmit: SubmitHandler<PaymentInputs> = (event) => {
    fetch("http://localhost:8100/api/transaction/request", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(event),
    })
      .then((resp) => resp.json())
      .then((data) =>
        window.location.replace(
          "https://sandbox.przelewy24.pl/trnRequest/" + data.token
        )
      );
    fetch("http://localhost:8080/bookings/");
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <DatePicker
        selected={startDate}
        minDate={new Date()}
        onChange={onChange}
        startDate={startDate}
        endDate={endDate}
        excludeDates={occupiedDatesTemp}
        selectsRange
        showDisabledMonthNavigation
        inline
      />
      <label>Amount</label>
      <Controller
        name="amount"
        control={control}
        defaultValue={0}
        render={({ field }) => <Input {...field} />}
      />
      <label>Currency</label>
      <Controller
        name="currency"
        control={control}
        defaultValue="PLN"
        render={({ field }) => <Input {...field} />}
      />
      <label>Email</label>
      <Controller
        name="email"
        control={control}
        defaultValue=""
        render={({ field }) => <Input {...field} />}
      />
      <label>Full Name</label>
      <Controller
        name="client"
        control={control}
        defaultValue=""
        render={({ field }) => <Input {...field} />}
      />
      <label>Phone number</label>
      <Controller
        name="phone"
        control={control}
        defaultValue=""
        render={({ field }) => <Input {...field} />}
      />
      <label>Description</label>
      <Controller
        name="description"
        control={control}
        defaultValue="Room booking"
        render={({ field }) => <Input {...field} />}
      />
      <input type="submit" />
    </form>
  );
}

export default PaymentCreate;
