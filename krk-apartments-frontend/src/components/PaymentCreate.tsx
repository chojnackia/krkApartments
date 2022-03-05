import { useForm, Controller, SubmitHandler } from "react-hook-form";
import Input from "@material-ui/core/Input";

interface PaymentInputs {
  amount: number;
  currency: string;
  email: string;
  phone: string;
  client: string;
  description: string;
}

export function PaymentCreate() {
  const { control, handleSubmit } = useForm<PaymentInputs>();

  const onSubmit: SubmitHandler<PaymentInputs> = (event) => {
    fetch("http://localhost:8100/api/transaction/request", {
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

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <label>Amount</label>
      <Controller
        name="amount"
        control={control}
        defaultValue={0}
        render={({ field }) => <Input {...field} />}
      />
      <label>Currency</label>
      <Controller
        name="currency"
        control={control}
        defaultValue="PLN"
        render={({ field }) => <Input {...field} />}
      />
      <label>Email</label>
      <Controller
        name="email"
        control={control}
        defaultValue=""
        render={({ field }) => <Input {...field} />}
      />
      <label>Full Name</label>
      <Controller
        name="client"
        control={control}
        defaultValue=""
        render={({ field }) => <Input {...field} />}
      />
      <label>Phone number</label>
      <Controller
        name="phone"
        control={control}
        defaultValue=""
        render={({ field }) => <Input {...field} />}
      />
      <label>Description</label>
      <Controller
        name="description"
        control={control}
        defaultValue="Room booking"
        render={({ field }) => <Input {...field} />}
      />
      <input type="submit" />
    </form>
  );
}

export default PaymentCreate;
