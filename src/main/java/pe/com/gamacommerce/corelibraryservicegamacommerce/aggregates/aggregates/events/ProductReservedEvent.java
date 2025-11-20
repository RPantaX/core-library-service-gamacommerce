package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import com.braidsbeautybyangie.sagapatternspringboot.aggregates.aggregates.dto.Product;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductReservedEvent {
    List<Product> productList;
    private Long shopOrderId;
    private Long reservationId;
}
