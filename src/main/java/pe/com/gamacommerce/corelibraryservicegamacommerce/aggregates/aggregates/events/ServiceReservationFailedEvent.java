package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto.Product;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReservationFailedEvent {
    private Long shopOrderId;
    private Long reservationId;
    private List<Product> productList;
}
