import React, { Component } from 'react'
import ApartmentService from '../services/ApartmentService'


class ApartmentDetails extends Component {

    constructor(props){
        super(props)

        this.state = {
            id: this.props.match.params.id,
            apartmentName: this.props.match.params.apartmentName,
            priceForOneDay: this.props.match.params.priceForOneDay,
            apartmentDescription: this.props.match.params.apartmentDescription,
        }
    }

    componentDidMount() {
        ApartmentService.getApartmentById(this.state.id).then((response) => {
            this.setState({apartment: response.data})
        });
    }
    


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
                            <tr>
                                <td>{this.apartmentName}</td>
                                <td>{this.priceForOneDay}</td>
                                <td>{this.apartmentDescription}</td>
                            </tr>   
                </tbody>
            </table>
            
        </div>
    )
    }
}

export default ApartmentDetails;
