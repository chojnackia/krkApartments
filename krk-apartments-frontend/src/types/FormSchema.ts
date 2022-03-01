import {FormSchema} from "react-booking-form"
let occupiedDatesTemp: Date[] = [];

export const formSchema: FormSchema = {
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