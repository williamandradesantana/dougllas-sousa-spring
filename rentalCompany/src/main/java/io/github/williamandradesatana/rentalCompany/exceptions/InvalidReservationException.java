package io.github.williamandradesatana.rentalCompany.exceptions;

public class InvalidReservationException extends RuntimeException {
    public InvalidReservationException() {
        super("Invalid reservation");
    }
}
