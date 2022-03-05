import tw from "twin.macro";
import styled from "@emotion/styled/macro";
import { BookingForm, DateInput, LocationOption } from "react-booking-form";
import { DatePickerInput } from "../layouts/DatePickerInput";
import axios from "axios";
import { IApartment } from "../interfaces/IApartment";
import { SliderValueLabel } from "@mui/material";

export const GuestButton = tw.button`appearance-none rounded-full p-2 flex items-center justify-center h-full overflow-hidden border border-gray-500 text-gray-500 hover:text-white hover:bg-green-500 hover:border-transparent transition ease-in-out disabled:opacity-50`;
export const Container = tw.div`rounded-full bg-white p-6 shadow-xl flex justify-between flex-col md:flex-row md:space-x-2 md:space-y-0 space-y-2`;
export const InputCore = tw.input`appearance-none border rounded-full w-full outline-none transition pl-4 pr-6 group-hover:border-green-500 focus:border-green-500 cursor-pointer`;
export const ControlCore = tw.div`appearance-none border rounded-full w-full outline-none transition pl-4 pr-6 group-hover:border-green-500 focus:border-green-500 cursor-pointer flex items-center`;
export const Placeholder = tw.div`text-gray-400 select-none`;
export const InputContainer = tw.div`relative w-full md:w-1/3 border-l-0 flex flex-col justify-center items-center md:border-l pl-2 first:border-l-0`;
export const Label = tw.div`text-sm w-full font-bold mb-1 text-gray-500`;

export const ButtonText = tw.div`ml-2`;
export const MainButton = tw.button`appearance-none mt-5 border-0 w-full h-10 rounded-full flex justify-center items-center bg-green-500 text-white font-bold px-3`;
export const IconContainer = tw.a`absolute top-0 right-0 bottom-0 h-full flex items-center pr-2 cursor-pointer text-gray-500 group-hover:text-green-400 transition`;

export const MenuContainer = styled.div<{ isOpen: boolean }>(({ isOpen }) => [
  tw`w-64 max-h-[240px] border z-10 mt-12 transform transition ease-in-out bg-white rounded-3xl overflow-y-auto overflow-x-hidden`,
  isOpen ? tw`opacity-100` : tw`opacity-0 -translate-y-4 pointer-events-none`,
]);
export const OptionBase = tw.div`transition ease-in-out relative py-2 px-4`;
export const OptionContainer = tw(
  OptionBase
)`hover:bg-green-100 cursor-pointer`;

const DatePicker = (
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

export const defaultLocationOptions = [
  { value: "new-york", label: "New York" },
  { value: "barcelona", label: "Barcelona" },
  { value: "los-angeles", label: "Los Angeles" },
];
