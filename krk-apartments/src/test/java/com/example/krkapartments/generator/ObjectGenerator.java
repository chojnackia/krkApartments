package com.example.krkapartments.generator;

import com.example.krkapartments.endpoint.address.dto.AddressDto;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.endpoint.booking.dto.BookingDto;
import com.example.krkapartments.endpoint.user.dto.UserDto;
import com.example.krkapartments.persistence.address.entity.AddressEntity;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.example.krkapartments.persistence.user.entity.UserEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ObjectGenerator {

    private final List<AddressEntity> addressEntityList = AddressGenerator.generateAddressList();
    private final List<AddressDto> addressDtos = AddressGenerator.generateAddressDtoList();
    private final List<ApartmentEntity> apartmentEntityList = ApartmentGenerator.generateApartmentList();
    private final List<ApartmentDto> apartmentDtos = ApartmentGenerator.generateApartmentDtoList();
    private final List<BookingEntity> bookingEntityList = BookingGenerator.generateBookingList();
    private final List<BookingDto> bookingDtoList = BookingGenerator.generateBookingDtoList();
    private final List<UserEntity> userEntityList = UserGenerator.generateUserList();
    private final List<UserDto> userDtoList = UserGenerator.generateUserDtoList();


    public void generateDependencies(List<ApartmentEntity> apartmentEntities, List<AddressEntity> addressEntities, List<BookingEntity> bookingEntities, List<UserEntity> userEntities) {
        apartmentEntities.get(0).setAddress(addressEntities.get(0));
        apartmentEntities.get(1).setAddress(addressEntities.get(1));
        apartmentEntities.get(2).setAddress(addressEntities.get(2));
        apartmentEntities.get(3).setAddress(addressEntities.get(3));
        apartmentEntities.get(4).setAddress(addressEntities.get(4));

        bookingEntities.get(0).setApartment(apartmentEntityList.get(0));
        bookingEntities.get(1).setApartment(apartmentEntityList.get(1));
        bookingEntities.get(2).setApartment(apartmentEntityList.get(0));

        bookingEntities.get(0).setUser(userEntities.get(0));
        bookingEntities.get(1).setUser(userEntities.get(1));
        bookingEntities.get(2).setUser(userEntities.get(0));

    }

    public void generateDtoDependencies(List<ApartmentDto> apartmentDtos, List<AddressDto> addressDtos, List<BookingDto> bookingDtos, List<UserEntity> userEntities) {

        bookingDtos.get(0).setApartmentId(apartmentEntityList.get(0).getId());
        bookingDtos.get(1).setApartmentId(apartmentEntityList.get(1).getId());
        bookingDtos.get(2).setApartmentId(apartmentEntityList.get(0).getId());

        bookingDtos.get(0).setUserEntity(userEntities.get(0));
        bookingDtos.get(1).setUserEntity(userEntities.get(1));
        bookingDtos.get(2).setUserEntity(userEntities.get(0));

    }

}