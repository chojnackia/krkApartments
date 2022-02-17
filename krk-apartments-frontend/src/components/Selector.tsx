import {
  GuestsSelect,
  LocationSelect,
  useReactBookingForm,
} from "react-booking-form";
import { FaSearch } from "react-icons/fa";
import { InputComponent } from "../layouts/InputComponent";
import {
  InputContainer,
  MenuContainer,
  OptionContainer,
  MainButton,
  ButtonText,
  Container,
  Label,
  DatePicker,
} from "../types/data";
import { formSchema } from "../types/FormSchema";
import { ControlComponent } from "./ControlComponent";
import { OptionComponent } from "../layouts/OptionComponent";

export const Selector = () => {
  const form = useReactBookingForm({ formSchema });

  return (
    <Container>
      <InputContainer>
        <Label>{"Location"}</Label>
        <LocationSelect
          form={form}
          menuContainer={MenuContainer}
          optionContainer={OptionContainer}
          inputComponent={InputComponent}
          name="location"
          inputProps={{ placeholder: "Where are you going?" }}
        />
      </InputContainer>
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
  );
};
