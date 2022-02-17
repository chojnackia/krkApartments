import { BookingForm as BookingFormType } from "react-booking-form";
import { FaUser } from "react-icons/fa";
import { ControlCore, IconContainer, Placeholder } from "../types/data";

// className="w-4 h-4" to FaUser
export const ControlComponent = ({
  form,
  name,
  placeholder,
  ...props
}: {
  form: BookingFormType;
  name: string;
  placeholder?: string;
}) => {
  const count = form.state[name].totalCount;
  return (
    <div className="relative flex group h-10 w-full">
      <ControlCore
        className="outline-none focus:outline-none"
        ref={form.refs[name] as any}
        tabIndex={-1}
        {...props}
      >
        <p>{count ? `${count} guest${count > 1 ? "s" : ""}` : ""} </p>
        <Placeholder>{count ? "" : placeholder}</Placeholder>
      </ControlCore>
      <IconContainer>
        <FaUser />
      </IconContainer>
    </div>
  );
};
