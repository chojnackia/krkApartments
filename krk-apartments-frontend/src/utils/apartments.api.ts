import Api from "./Api"

const getApartmentsList = async () => {
    const response = await Api.get('/apartments');
    return response.data;
}

export {getApartmentsList};