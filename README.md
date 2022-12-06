# krkApartments - Microservices architecture for apartments booking application
This project is about something like a booking, airbnb etc. alternative for private people that owns a small amount of apartments for shor-term rental. 
For now, appliaction has working backend services like CRUD operations for apartments, bookings etc., also for users that already have their account on booking.com, calendars are synchronized every minute, so we shouldn't have situations where we have two reservations for the same date. 
The frontend of the application is currently only for checking the functionality of payment serivce (https://www.przelewy24.pl/). In the near future plans it is planned to create fully functional frontend and connect it with backend. 



**Build instruction**

Project can be build by maven
```
mvn clean build
```
**Docker instruction**
Build docker image
```
docker-compose docker-compose.yml build
```
Run Docker Images
```
docker-compose docker-compose.yml up
```
