package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.requests;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestProductsEvent {
    private Long productId;
    private Integer quantity;
}
