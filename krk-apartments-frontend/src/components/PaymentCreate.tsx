import React, { useState } from "react";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";

type PaymentProps = {
    amount: number,
    currency: string,
    email: string,
    phone: string,
    client: string,
    description: string,
    token: string,
}

export const PaymentCreate = (props: PaymentProps) => {
    const [amount, setAmount] = useState('1000')
    const [currency, setCurrency] = useState('PLN')
    const [email, setEmail] = useState('chojnackiadaam@op.pl')
    const [phone, setPhone] = useState('784308709')
    const [client, setClient] = useState('Adam Chojnacki')
    const [description, setDescription] = useState('aaaaa')

    const handleAmountChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const enteredAmount = event.target.value;
        setAmount(enteredAmount);
    }

    const handleCurrencyChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const enteredCurrency = event.target.value;
        setCurrency(enteredCurrency);
    }

    const handleEmailChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const enteredEmail = event.target.value;
        setEmail(enteredEmail);
    }

    const handlePhoneChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const enteredPhone = event.target.value;
        setPhone(enteredPhone);
    }

    const handleClientChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const enteredClient = event.target.value;
        setClient(enteredClient);
    }

    const handleDescriptionChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const enteredDescription = event.target.value;
        setDescription(enteredDescription);
    }
    


    const handleSubmit : React.FormEventHandler<HTMLFormElement> =  (event)  => {
            const item = {
        amount: amount,
        currency: currency,
        email: email,
        phone: phone,
        client: client,
        description: description,
        //token: token,
    };

        event.preventDefault();
        
        fetch('/api/transaction/request', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then(resp => resp.json())
        .then(data => window.location.replace('https://sandbox.przelewy24.pl/trnRequest/' + data.token));
    }

    return(
        <Container>
        <Form onSubmit={handleSubmit}>
            <FormGroup>
                <Label for="amount">Amount</Label>
                <Input type="text" name="amount" id="amount" value={amount}
                       onChange={handleAmountChange} autoComplete="amount"/>
            </FormGroup>
            <FormGroup>
                <Label for="currency">Currency</Label>
                <Input type="text" name="currency" id="currency" value={currency}
                       onChange={handleCurrencyChange} autoComplete="currency"/>
            </FormGroup>
            <FormGroup>
                <Label for="email">Email</Label>
                <Input type="text" name="email" id="email" value={email}
                       onChange={handleEmailChange} autoComplete="email"/>
            </FormGroup>
            <FormGroup>
            <Label for="client">Full Name</Label>
            <Input type="text" name="client" id="client" value={client}
                   onChange={handleClientChange} autoComplete="client"/>
            </FormGroup>
            <FormGroup>
                <Label for="phone">Telephone Number</Label>
                <Input type="text" name="phone" id="phone" value={phone}
                       onChange={handlePhoneChange} autoComplete="phone"/>
            </FormGroup>
            <FormGroup>
                <Label for="description">Description</Label>
                <Input type="text" name="description" id="description" value={description}
                       onChange={handleDescriptionChange} autoComplete="description"/>
            </FormGroup>
            <FormGroup>
                <Button color="primary" type="submit">Save</Button>{' '}
            </FormGroup>
        </Form>
    </Container>
    )
}

export default PaymentCreate