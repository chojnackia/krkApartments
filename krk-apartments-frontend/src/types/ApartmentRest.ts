import axios from 'axios'
import { IApartment } from '../interfaces/IApartment'

   export const getAllApartments = async () => {
            axios.get<IApartment[]>('http://localhost:8080/apartments/')
            .then((data: any) => {
                    data.map((data: IApartment) => (
                        [{ value: data.id, label: data.apartmentName}]))
                        console.log("udalo sie");
            })
     }
                    
    