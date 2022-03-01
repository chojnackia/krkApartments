import axios from "axios";
import { useEffect, useState } from "react";
import {
  FormSchema,
  //FormSchema,
  GuestsSelect,
  useReactBookingForm,
} from "react-booking-form";
import { SubmitHandler, useForm } from "react-hook-form";
import { FaSearch } from "react-icons/fa";
import { useParams } from "react-router-dom";
import { ControlComponent } from "../components/ControlComponent";
import { IBooking } from "../interfaces/IBooking";
import {
  InputContainer,
  MenuContainer,
  MainButton,
  ButtonText,
  Container,
  Label,
} from "../types/data";
import { DatePicker } from "./DatePicker";
import { formSchema } from "../types/FormSchema";
import { OptionComponent } from "./OptionComponent";

export function BookingForm() {
  const params = useParams();

  interface PaymentInputs {
    amount: number;
    currency: string;
    email: string;
    phone: string;
    client: string;
    description: string;
  }

  const [bookings, setBookings] = useState<IBooking[]>();
  const { control, handleSubmit } = useForm<FormSchema>();

  const occupiedDatesTemp: Date[] = [];

  useEffect(() => {
    window.addEventListener("mousemove", () => {});
    axios
      .get(`http://localhost:8080/bookings/apartments/${params.id}`)
      .then(({ data }) => {
        console.log(data);
        data.map((data: IBooking) => {
          var start = new Date(data.checkInDate);
          var end = new Date(data.checkOutDate);
          var date = new Date(start);

          while (date <= end) {
            occupiedDatesTemp.push(new Date(date));
            date.setDate(date.getDate().valueOf() + 1);
          }
        });
        console.log(occupiedDatesTemp);
      });
  }, [occupiedDatesTemp, params.id]);

  const onSubmit: SubmitHandler<PaymentInputs> = (event) => {
    fetch("http://localhost:8080/bookings/", {
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
  };
  const formSchema: FormSchema = {
    checkIn: {
      type: "date",
      focusOnNext: "checkOut",
      options: {
        altInput: true,
        altFormat: "M j, Y",
        dateFormat: "Y-m-d",
        minDate: "today",
        disable: [
          function (date: Date) {
            return occupiedDatesTemp.toString().includes(date.toDateString());
          },
        ],
        wrap: true,
      },
    },
    checkOut: {
      type: "date",
      focusOnNext: "guests",
      options: {
        minDateFrom: "checkIn",
        altInput: true,
        altFormat: "M j, Y",
        dateFormat: "Y-m-d",
        disable: [
          function (date: Date) {
            return occupiedDatesTemp.toString().includes(date.toDateString());
          },
        ],
        wrap: true,
      },
    },
    guests: {
      type: "peopleCount",
      defaultValue: [
        {
          name: "adults",
          label: "Adults",
          description: "Ages 13+",
          value: 1,
          min: 0,
          max: 10,
        },
        {
          name: "children",
          label: "Children",
          description: "Ages 4-12",
          value: 0,
          min: 0,
          max: 10,
        },
        {
          name: "infants",
          label: "Infants",
          description: "Under 4 years old",
          value: 0,
          min: 0,
          max: 10,
        },
      ],
    },
  };

  const form = useReactBookingForm({ formSchema });
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <Container>
        <InputContainer>
          <Label>{"Check in"}</Label>
          <DatePicker placeholder="Add date" form={form} name={"checkIn"} />
        </InputContainer>
        <InputContainer>
          <Label>{"Check out"}</Label>
          <DatePicker placeholder="Add date" form={form} name={"checkOut"} />
        </InputContainer>
        <InputContainer>
          <Label>{"Guests"}</Label>
          <GuestsSelect
            form={form}
            menuContainer={MenuContainer}
            optionComponent={OptionComponent}
            controlComponent={ControlComponent}
            controlProps={{ placeholder: "Add guests" }}
            name={"guests"}
          />
        </InputContainer>
        <InputContainer>
          <MainButton>
            <FaSearch />
            <ButtonText>{"Search"}</ButtonText>
          </MainButton>
        </InputContainer>
      </Container>
    </form>
  );
}
