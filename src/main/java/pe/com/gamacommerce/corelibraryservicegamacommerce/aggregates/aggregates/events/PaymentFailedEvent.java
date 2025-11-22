package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto.Product;
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
}
