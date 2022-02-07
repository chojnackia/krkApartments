import axios, {AxiosInstance} from 'axios';

class Api {
    private static axiosInstance: AxiosInstance;

    static init(){
        this.axiosInstance = axios.create({
            baseURL: 'http://localhost:8080'
        })
    }

    static async get<ResponseType>(url: string) {
        return await Api.axiosInstance.get<ResponseType>(url)
    }

}
export default Api;