export interface IApartment {
    id: number,
    apartmentName: string,
    priceForOneDay: number,
    apartmentDescription: string,
    address: {
        city: string,
        streetName: string,
        buildingNumber: number,
        apartmentNumber: number,
        postCode: string,
        country: string,
    }
}