package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import com.braidsbeautybyangie.sagapatternspringboot.aggregates.aggregates.dto.Product;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PaymentFailedEvent {
    private Long shopOrderId;
    private List <Product> productList;
    private Long reservationId;
}
