import React, { Component } from 'react';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';

class PaymentCreate extends Component {
    
    emptyItem = {
        amount: '',
        currency: '',
        email: '',
        phone: '',
        client: '',
        description: '',
        token: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            item: this.emptyItem
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        if(this.props.match.params.id !== 'new') {
            const payment = await (await fetch(`/api/transaction/request`)).json();
            this.setState({item: payment});
        }
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const {item} = this.state;
    
        await fetch('/api/transaction/request', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        }).then(resp => resp.json())
        .then(data => this.setState({token: data.token},
        window.location.replace('https://sandbox.przelewy24.pl/trnRequest/' + data.token),
        )
        );
    }
    
    render() {
        const {item} = this.state;
        const title = <h2>{item.id ? 'Edit Payment' : 'Add Payment'}</h2>;
    
        return <div>
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="amount">Amount</Label>
                        <Input type="text" name="amount" id="amount" value={item.amount || ''}
                               onChange={this.handleChange} autoComplete="amount"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="currency">Currency</Label>
                        <Input type="text" name="currency" id="currency" value={item.currency || ''}
                               onChange={this.handleChange} autoComplete="currency"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="email">Email</Label>
                        <Input type="text" name="email" id="email" value={item.email || ''}
                               onChange={this.handleChange} autoComplete="email"/>
                    </FormGroup>
                    <FormGroup>
                    <Label for="client">Full Name</Label>
                    <Input type="text" name="client" id="client" value={item.client || ''}
                           onChange={this.handleChange} autoComplete="client"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="phone">Telephone Number</Label>
                        <Input type="text" name="phone" id="phone" value={item.phone || ''}
                               onChange={this.handleChange} autoComplete="phone"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="description">Description</Label>
                        <Input type="text" name="description" id="description" value={item.description || ''}
                               onChange={this.handleChange} autoComplete="description"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                    </FormGroup>
                </Form>
            </Container>
        </div>
    }
    
}
export default PaymentCreate;