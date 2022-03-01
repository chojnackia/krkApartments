import { BookingForm, DateInput } from "react-booking-form";
import { DatePickerInput } from "./DatePickerInput";

export const DatePicker = (
  props: JSX.IntrinsicAttributes & {
    placeholder?: string | undefined;
    inputComponent?: any;
    className?: string | undefined;
    form: BookingForm;
    name: string;
  }
) => (
  <DateInput className="w-full" inputComponent={DatePickerInput} {...props} />
);
