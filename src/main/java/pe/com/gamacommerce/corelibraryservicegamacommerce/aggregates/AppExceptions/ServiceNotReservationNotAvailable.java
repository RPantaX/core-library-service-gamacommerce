package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

import lombok.Getter;

@Getter
public class ServiceNotReservationNotAvailable extends RuntimeException{
    private final Long reservationId;
    private final Long shopOrderId;
    public ServiceNotReservationNotAvailable(Long reservationId, Long shopOrderId) {
        super("Service not available for reservationId: " + reservationId + " and shopOrderId: " + shopOrderId);
        this.reservationId = reservationId;
        this.shopOrderId = shopOrderId;
    }
}
