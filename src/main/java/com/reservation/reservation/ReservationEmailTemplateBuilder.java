package com.reservation.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationEmailTemplateBuilder {

    private final TemplateEngine templateEngine;

    public String buildConfirmationEmail(String firstName, String lastName, Long reservationNumber, LocalDate checkIn, LocalDate checkOut, List<String> rooms) {
        Context context = new Context();
        context.setVariable("firstName", firstName);
        context.setVariable("lastName", lastName);
        context.setVariable("reservationNumber", reservationNumber);
        context.setVariable("checkIn", checkIn.toString());
        context.setVariable("checkOut", checkOut.toString());
        context.setVariable("rooms", rooms);

        return templateEngine.process("reservation-confirmation", context);
    }

    public String buildCancellationEmail(String firstName, Long reservationNumber) {
        Context context = new Context();
        context.setVariable("firstName", firstName);
        context.setVariable("reservationNumber", reservationNumber);

        return templateEngine.process("reservation-cancellation", context);
    }

}
