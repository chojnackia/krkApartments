package com.example.krkapartments.generator;

import com.example.krkapartments.module.address.Address;
import com.example.krkapartments.module.address.AddressDto;
import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentDto;
import com.example.krkapartments.module.booking.Booking;
import com.example.krkapartments.module.booking.BookingDto;
import com.example.krkapartments.module.user.User;
import com.example.krkapartments.module.user.UserDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ObjectGenerator {

    private final List<Address> addressList = AddressGenerator.generateAddressList();
    private final List<AddressDto> addressDtos = AddressGenerator.generateAddressDtoList();
    private final List<Apartment> apartmentList = ApartmentGenerator.generateApartmentList();
    private final List<ApartmentDto> apartmentDtos = ApartmentGenerator.generateApartmentDtoList();
    private final List<Booking> bookingList = BookingGenerator.generateBookingList();
    private final List<BookingDto> bookingDtoList = BookingGenerator.generateBookingDtoList();
    private final List<User> userList = UserGenerator.generateUserList();
    private final List<UserDto> userDtoList = UserGenerator.generateUserDtoList();


    public void generateDependencies(List<Apartment> apartments, List<Address> addresses, List<Booking> bookings, List<User> users) {
        apartments.get(0).setAddress(addresses.get(0));
        apartments.get(1).setAddress(addresses.get(1));
        apartments.get(2).setAddress(addresses.get(2));
        apartments.get(3).setAddress(addresses.get(3));
        apartments.get(4).setAddress(addresses.get(4));

        bookings.get(0).setApartment(apartmentList.get(0));
        bookings.get(1).setApartment(apartmentList.get(1));
        bookings.get(2).setApartment(apartmentList.get(0));

        bookings.get(0).setUser(users.get(0));
        bookings.get(1).setUser(users.get(1));
        bookings.get(2).setUser(users.get(0));

    }

    public void generateDtoDependencies(List<ApartmentDto> apartmentDtos, List<AddressDto> addressDtos, List<BookingDto> bookingDtos, List<User> users) {

        bookingDtos.get(0).setApartmentId(apartmentList.get(0).getId());
        bookingDtos.get(1).setApartmentId(apartmentList.get(1).getId());
        bookingDtos.get(2).setApartmentId(apartmentList.get(0).getId());

        bookingDtos.get(0).setUser(users.get(0));
        bookingDtos.get(1).setUser(users.get(1));
        bookingDtos.get(2).setUser(users.get(0));

    }

}