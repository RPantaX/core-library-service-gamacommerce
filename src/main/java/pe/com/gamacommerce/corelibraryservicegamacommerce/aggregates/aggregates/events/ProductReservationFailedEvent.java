package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.requests.RequestProductsEvent;
import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReservationFailedEvent {
    private Long shopOrderId;
    List<RequestProductsEvent> requestProductsEventList;
}
