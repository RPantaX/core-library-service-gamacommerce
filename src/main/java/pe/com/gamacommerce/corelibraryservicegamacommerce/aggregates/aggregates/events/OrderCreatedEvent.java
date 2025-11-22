package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.requests.RequestProductsEvent;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreatedEvent {
    private Long shopOrderId;
    private Long customerId;
    private List<RequestProductsEvent> requestProductsEventList;
}
