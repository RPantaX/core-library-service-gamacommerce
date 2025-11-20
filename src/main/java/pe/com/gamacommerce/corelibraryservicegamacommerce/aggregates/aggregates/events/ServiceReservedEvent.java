package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto.Product;
import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto.ReservationCore;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceReservedEvent {
    private Long shopOrderId;
    private ReservationCore reservationCore;
    List<Product> productList;
}
