package com.example.krkapartments.business.calendar;

import com.example.krkapartments.business.apartment.ApartmentService;
import com.example.krkapartments.domain.apartment.Apartment;
import com.example.krkapartments.domain.apartment.ApartmentMapper;
import com.example.krkapartments.endpoint.apartment.dto.ApartmentDto;
import com.example.krkapartments.persistence.apartment.entity.ApartmentEntity;
import com.example.krkapartments.persistence.booking.entity.BookingEntity;
import com.example.krkapartments.persistence.booking.repository.BookingRepository;
import lombok.AllArgsConstructor;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.*;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.*;
import net.fortuna.ical4j.validate.ValidationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
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
    ApartmentMapper apartmentMapper;

    @Scheduled(fixedRate = 1000 * 60) //Calendar synchronization every 60 seconds
    public void synchronizeCalendar() throws IOException, ParserException {
        List<ApartmentDto> apartmentDtos = apartmentService.findAllActiveApartments();
        List<Apartment> apartments = apartmentDtos.stream()
                .map(apartmentMapper::mapFromDtoToDomain)
                .collect(Collectors.toList());

        for (Apartment apartment : apartments) {
            URL bookingUrl = new URL(apartment.getBookingUrl());
            try (InputStream is = bookingUrl.openStream()) {
                Calendar c = new CalendarBuilder().build(is);
                List<VEvent> events = c.getComponents(Component.VEVENT);
                ApartmentEntity apartmentEntityInDatabase = apartmentService.findApartmentInDatabase(apartment.getId());

                for (VEvent event : events) {
                    LocalDate startDate = event.getStartDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate endDate = event.getEndDate().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    List<BookingEntity> occupiedApartments = bookingRepository.findAllByApartmentEqualsAndCheckInDateIsBetweenOrCheckOutDateIsBetween(
                            apartmentService.findApartmentInDatabase(apartment.getId()),
                            startDate,
                            endDate);

                    if (occupiedApartments.isEmpty()) {
                        bookingRepository.save(BookingEntity.builder()
                                .id(UUID.randomUUID())
                                .checkInDate(startDate)
                                .checkOutDate(endDate)
                                .apartment(apartmentEntityInDatabase)
                                .build());
                    }
                }
            }
        }
    }

    @Scheduled(fixedRate = 1000 * 60 * 60) //Calendar synchronization every 60 seconds
    public void createCalendarFile() throws ParseException {

        Calendar calendar = new Calendar();

        String eventSummary = " CLOSED - Not available";
        VEvent event = new VEvent(Boolean.parseBoolean(eventSummary));

        PropertyList<Property> eventProps = event.getProperties();

        java.time.ZonedDateTime now = java.time.ZonedDateTime.now();

        String uidTimestamp = java.time.format.DateTimeFormatter
                .ofPattern("uuuuMMdd'T'hhmmssXX")
                .format(now);
        //In the real world this could be a number from a generated sequence:
        String uidSequence = "/" + (int) Math.ceil(Math.random() * 1000);
        String uidDomain = "@krk.apartments.com";


        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(new ProdId("-//KrkApartments//iCal4j 1.0//EN"));
        calendar.getProperties().add(CalScale.GREGORIAN);
        calendar.getProperties().add(Method.PUBLISH);
        calendar.getComponents().add(event);


        eventProps.add(new DtStart(new Date("20220101")));
        eventProps.add(new DtEnd(new Date("20220303")));
        eventProps.add(new Uid(uidTimestamp + uidSequence + uidDomain));
        eventProps.add(new Summary(eventSummary));

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

    }
}

