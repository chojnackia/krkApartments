export interface IBooking{
    apartmentId: string,
    checkInDate: Date,
    checkOutDate: Date,
    paymentStatus: boolean;
    user: {
        firstName: string,
        lastName: string,
        telephoneNumber: string,
        email: string,
    }
}