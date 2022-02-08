package com.example.paymentservice.przelewy24.consumer;

import com.example.paymentservice.przelewy24.user.UserDto;

public interface RestConsumer {

    void sendDataForUserCreation(UserDto userDto);

}
