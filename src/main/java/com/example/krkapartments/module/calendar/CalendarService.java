package com.example.krkapartments.module.calendar;

import com.example.krkapartments.module.apartment.Apartment;
import com.example.krkapartments.module.apartment.ApartmentConverter;
import com.example.krkapartments.module.apartment.ApartmentDto;
import com.example.krkapartments.module.apartment.ApartmentService;
import com.example.krkapartments.module.booking.Booking;
import com.example.krkapartments.module.booking.BookingRepository;
import lombok.AllArgsConstructor;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.validate.ValidationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
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

        for (Apartment apartment : apartments) {
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

  /*  @Scheduled(fixedRate = 1000 * 60 * 60) //Calendar synchronization every 60 seconds
    public void createCalendarFile() {

        Calendar calendar = new Calendar();

        String eventSummary = "CLOSED - Not available";
        VEvent event = new VEvent(Boolean.parseBoolean(eventSummary));

        PropertyList<Property> eventProps = event.getProperties();

        java.time.ZonedDateTime now = java.time.ZonedDateTime.now();

        String uidTimestamp = java.time.format.DateTimeFormatter
                .ofPattern("uuuuMMdd'T'hhmmssXX")
                .format(now);
        //In the real world this could be a number from a generated sequence:
        String uidSequence = "/" + (int) Math.ceil(Math.random() * 1000);
        String uidDomain = "@krk.apartments.com";

        eventProps.add(new Uid(uidTimestamp + uidSequence + uidDomain));

        calendar.getProperties().add(new ProdId("-//KrkApartments//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        calendar.getComponents().add(event);

        String filePath = "export.ics";
        FileOutputStream fout = null;
        try {

            fout = new FileOutputStream(filePath);
            CalendarOutputter outputter = new CalendarOutputter();
            outputter.output(calendar, fout);

        } catch (ValidationException | IOException e) {
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
}

