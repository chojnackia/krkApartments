import React, {useState, useEffect} from 'react'
import axios from 'axios'
import { IApartment } from '../../interfaces/IApartment'
import { Link } from 'react-router-dom'

export default function ApartmentList(){
    const [apartments, setApartments] = useState<IApartment[]>([])

    useEffect(() => {
        axios.get<IApartment[]>('http://localhost:8080/apartments/')
        .then(({data}) => {
            setApartments(data)
        })
    },[])

    return(
        <div>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <td>Name</td>
                        <td>Price for one day</td>
                        <td>Apartment Description</td>
                        <td>Country</td>
                        <td>City</td>
                        <td>Street Name</td>
                        <td>Building number</td>
                        <td>Apartment number</td>
                        <td>Post Code</td>
                        <td>Actions</td>
                     </tr>

                 </thead>
                 <tbody>
                    {apartments.map((apartment: IApartment) => (
                     <tr key={apartment.id}>
                         <td>{apartment.apartmentName}</td>
                         <td>{apartment.priceForOneDay}</td>
                         <td>{apartment.apartmentDescription}</td>
                         <td>{apartment.address.country}</td>
                         <td>{apartment.address.city}</td>
                         <td>{apartment.address.streetName}</td>
                         <td>{apartment.address.buildingNumber}</td>
                         <td>{apartment.address.apartmentNumber}</td>
                         <td>{apartment.address.postCode}</td>
                         <td>
                             <Link to={`/apartments/${apartment.id}`} className='btn btn-primary mb-2'>Details</Link>
                         </td>
                     </tr>
                 ))}
                 </tbody>
           </table>
        </div>
    )
}
