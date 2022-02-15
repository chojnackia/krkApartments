import { numberTypeAnnotation } from '@babel/types';
import axios from 'axios'
import React, { useEffect, useState } from 'react'
import context from 'react-bootstrap/esm/AccordionContext';
import { Link, useParams } from 'react-router-dom';
import { IApartment } from '../interfaces/IApartment'

export function ApartmentDetails() {

  const [apartment, setApartment] = useState<IApartment>()

  const params = useParams();

     useEffect(() => {
         axios.get(`http://localhost:8080/apartments/${params.id}`)
         .then(res => {
           console.log(res)
          setApartment(res.data)
      })
    }, [params.id]);
      
  return (
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
                     <tr>
                         <td>{apartment?.apartmentName}</td>
                         <td>{apartment?.priceForOneDay}</td>
                         <td>{apartment?.apartmentDescription}</td>
                         <td>{apartment?.address.country}</td>
                         <td>{apartment?.address.city}</td>
                         <td>{apartment?.address.streetName}</td>
                         <td>{apartment?.address.buildingNumber}</td>
                         <td>{apartment?.address.apartmentNumber}</td>
                         <td>{apartment?.address.postCode}</td>
                         <td>
                             <Link to={`/apartments`} className='btn btn-primary mb-2'>Details</Link>
                         </td>
                     </tr>
                 </tbody>
           </table>
        </div>
  )
}