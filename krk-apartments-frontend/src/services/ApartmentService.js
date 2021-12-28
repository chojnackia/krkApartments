import axios from "axios";

class ApartmentService {
    
    getApartments(){
        return axios.get('/apartments/');
    }

    getApartmentById(id){
        return axios.get('/apartment/${id}');
    }
}

export default new ApartmentService();