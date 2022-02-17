import {FormSchema} from "react-booking-form"
import { defaultLocationOptions,} from "./data"
export const formSchema: FormSchema = {
        location: {
          type: "location",
          focusOnNext: "checkIn",
          options: { defaultLocationOptions, }
        },
        checkIn: {
          type: "date",
          focusOnNext: "checkOut",
          options: {
            altInput: true,
            altFormat: "M j, Y",
            dateFormat: "Y-m-d",
            minDate: "today",
            wrap: true
          }
        },
        checkOut: {
          type: "date",
          focusOnNext: "guests",
          options: {
            minDateFrom: "checkIn",
            altInput: true,
            altFormat: "M j, Y",
            dateFormat: "Y-m-d",
            wrap: true
          }
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
              max: 10
            },
            {
              name: "children",
              label: "Children",
              description: "Ages 4-12",
              value: 0,
              min: 0,
              max: 10
            },
            {
              name: "infants",
              label: "Infants",
              description: "Under 4 years old",
              value: 0,
              min: 0,
              max: 10
            }
          ]
        }
      }
