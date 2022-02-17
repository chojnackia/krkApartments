import { FaPlus, FaMinus } from "react-icons/fa";
import { OptionBase, GuestButton } from "../types/data";
import { BookingForm as BookingFormType } from "react-booking-form";

export const OptionComponent = ({
  form,
  name,
  option,
}: {
  form: BookingFormType;
  name: string;
  option: any;
}) => {
  const onPlusClick = () => {
    form.setGuestOptionValue(name, option, option.value + 1);
  };

  const onMinusClick = () => {
    form.setGuestOptionValue(name, option, option.value - 1);
  };

  return (
    <OptionBase className="flex justify-between items-center">
      <div>
        <p className="font-title font-bold text-sm text-gray-700">
          {option.label}
        </p>
        <p className="text-gray-500 text-sm">{option.description}</p>
      </div>
      <div className="flex justify-center items-center gap-x-2">
        <GuestButton
          onClick={onPlusClick}
          disabled={option.value >= (option.max || 100)}
        >
          <FaPlus />
        </GuestButton>
        <p className="font-title font-bold text-sm text-gray-700">
          {option.value}
        </p>
        <GuestButton onClick={onMinusClick} disabled={option.value === 0}>
          <FaMinus />
        </GuestButton>
      </div>
    </OptionBase>
  );
};
