import React, { Component } from 'react'
import ApartmentService from '../services/ApartmentService'
import { useNavigate } from 'react-router-dom'

import MyButton from './MyButton'

 class ApartmentList extends Component {

    constructor(props) {
        super(props)
    
        this.state = {
             apartments: []
        }
     //   this.apartmentDetailsClicked = this.apartmentDetailsClicked.bind(this)
    }
    
    componentDidMount() {
        ApartmentService.getApartments().then((response) => {
            this.setState({apartments: response.data})
        });
    }

    // apartmentDetailsClicked(id) {
    //     console.log('apartment' + id);
        
    // }
    
    render() {
        return (
            <div>
                <h1 className="text-center table-bordered">Apartments List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Apartment Name</td>
                            <td>Price</td>
                            <td>Description</td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.apartments.map(
                                apartment =>
                                <tr key={apartment.id}>
                                    <td>{apartment.apartmentName}</td>
                                    <td>{apartment.priceForOneDay}</td>
                                    <td>{apartment.apartmentDescription}</td>
                                    <td>
                                        <MyButton />
                                        {/* <button onClick = { () => this.apartmentDetailsClicked(apartment.id)} className="btn btn-info">Check Apartment Details!</button> */}
                                    </td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
                
            </div>
        )
    }
}


export default ApartmentList;
