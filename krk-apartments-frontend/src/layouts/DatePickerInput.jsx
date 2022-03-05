import { FaCalendarAlt } from "react-icons/fa";
import { IconContainer, InputCore } from "../types/data";

 export const DatePickerInput = ({ placeholder, inputRef }) => (
    <div className="relative flex group h-10 w-full" ref={inputRef}>
      <InputCore
        ref={inputRef}
        type="input"
        data-input
        placeholder={placeholder}
      />
      <IconContainer title="toggle" data-toggle>
        <FaCalendarAlt className="w-4 h-4" />
      </IconContainer>
    </div>
  )