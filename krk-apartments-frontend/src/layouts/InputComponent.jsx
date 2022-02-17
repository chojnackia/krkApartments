import { FaSpinner, FaMapMarkerAlt } from "react-icons/fa";
import { InputCore, IconContainer } from "../types/data";

export const InputComponent = ({ form, name, isLoading, ...props }) => (
    <div className="relative flex group h-10 w-full">
      <InputCore
        className="outline-none focus:outline-none"
        ref={form.refs[name]}
        {...props}
      />
      <IconContainer>
        {isLoading ? (
          <FaSpinner className="w-4 h-4 animate-spin" />
        ) : (
          <FaMapMarkerAlt className="w-4 h-4" />
        )}
      </IconContainer>
    </div>
  )