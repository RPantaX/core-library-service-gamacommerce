package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.commands;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductAndServiceReservationCancelledEvent {
    private Long[] productIds;
    private Long shopOrderId;
    private Long reservationId;
}
