import axios from "axios";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import { useParams } from "react-router-dom";
import { IBooking } from "../interfaces/IBooking";

import "react-datepicker/dist/react-datepicker.css";

import { Container } from "@material-ui/core";

export function BookingForm() {
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
  console.log(startDate, endDate);
  return (
    <form>
      <DatePicker
        selected={startDate}
        minDate={new Date()}
        onChange={onChange}
        startDate={startDate}
        endDate={endDate}
        excludeDates={occupiedDatesTemp}
        selectsRange
        // selectsDisabledDaysInRange
        inline
      />
    </form>
  );
}
