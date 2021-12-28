package com.example.krkapartments.module.calendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.UUID;

@Service
public class CalendarService {

    public void createCalendarFile() {
        /* Event start and end time in milliseconds */
        Long startDateTimeInMillis = 1615956275000L;
        Long endDateTimeInMillis = 1615959875000L;

       // Calendar calendarStartTime = new GregorianCalendar();
        //calendarStartTime.setTimeInMillis(startDateTimeInMillis);

        // Time zone info
        TimeZone tz = calendarStartTime.getTimeZone();
        ZoneId zid = tz.toZoneId();

        /* Generate unique identifier */
        UUID uuid = UUID.randomUUID();

        /* Create the event */
        String eventSummary = "Happy New Year";
       // LocalDateTime start = LocalDateTime.ofInstant(calendarStartTime.toInstant(), zid);
       // LocalDateTime end = LocalDateTime.ofInstant(Instant.ofEpochMilli(endDateTimeInMillis), zid);
        VEvent event = new VEvent(eventSummary);
        event.getProperties().add(uuid);

        /* Create calendar */
        Calendar calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//Ben Fortuna//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        /* Add event to calendar */
        calendar.getComponents().add(event);

        /* Create a file */
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
    }
}
