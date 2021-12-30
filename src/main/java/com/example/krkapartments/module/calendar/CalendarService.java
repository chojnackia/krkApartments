package com.example.krkapartments.module.calendar;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentConverter;
import com.example.krkapartments.module.apartment.ApartmentDto;
import com.example.krkapartments.module.apartment.ApartmentService;
import com.example.krkapartments.module.booking.Booking;
import com.example.krkapartments.module.booking.BookingRepository;
import lombok.AllArgsConstructor;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableScheduling
public class CalendarService {

    BookingRepository bookingRepository;
    ApartmentService apartmentService;

    @Scheduled(fixedRate = 1000 * 60) //Calendar synchronization every 60 seconds
    public void synchronizeCalendar() throws IOException, ParserException {
        List<ApartmentDto> apartmentDtos = apartmentService.findAllActiveApartments();
        List<Apartment> apartments = apartmentDtos.stream()
                .map(ApartmentConverter::convertDtoToApartment)
                .collect(Collectors.toList());

        for (Apartment apartment : apartments){
            URL bookingUrl = new URL(apartment.getBookingUrl());
            try (InputStream is = bookingUrl.openStream()) {
                Calendar c = new CalendarBuilder().build(is);
                VEvent vEvent = new VEvent();
                List<VEvent> events = c.getComponents(Component.VEVENT);
                Apartment apartmentInDatabase = apartmentService.findApartmentInDatabase(apartment.getId());

                for (VEvent event : events) {
                    LocalDate startDate = event.getStartDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endDate = event.getEndDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    List<Booking> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                            apartmentService.findApartmentInDatabase(apartment.getId()),
                            startDate,
                            endDate);

                    if (occupiedApartments.isEmpty()) {
                        bookingRepository.save(Booking.builder()
                                .id(UUID.randomUUID())
                                .checkInDate(startDate)
                                .checkOutDate(endDate)
                                .apartment(apartmentInDatabase)
                                .build());
                    }
                }
            }
        }
    }
}

/*  public void createCalendarFile() {

 *//* Event start and end time in milliseconds *//*
        Long startDateTimeInMillis = 1615956275000L;
        Long endDateTimeInMillis = 1615959875000L;

        // Calendar calendarStartTime = new GregorianCalendar();
        //calendarStartTime.setTimeInMillis(startDateTimeInMillis);

        // Time zone info
        TimeZone tz = calendarStartTime.getTimeZone();
        ZoneId zid = tz.toZoneId();

        *//* Generate unique identifier *//*
        UUID uuid = UUID.randomUUID();

        *//* Create the event *//*
        String eventSummary = "Happy New Year";
        // LocalDateTime start = LocalDateTime.ofInstant(calendarStartTime.toInstant(), zid);
        // LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(endDateTimeInMillis), zid);
        VEvent event = new VEvent(eventSummary);
        event.getProperties().add(uuid);

        *//* Create calendar *//*
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        *//* Add event to calendar *//*
        calendar.getComponents().add(event);

        *//* Create a file *//*
        String filePath = "mymeeting.ics";
        FileOutputStream fout = null;
        try {

            fout = new FileOutputStream(filePath);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fout != null) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

