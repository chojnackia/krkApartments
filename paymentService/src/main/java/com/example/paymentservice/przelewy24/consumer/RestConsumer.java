package com.example.paymentservice.przelewy24.consumer;

import com.example.paymentservice.przelewy24.User.UserDto;

public interface RestConsumer {

    void sendDataForUserCreation(UserDto userDto);

}
