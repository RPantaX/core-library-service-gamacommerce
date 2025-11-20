package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.events;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PaymentProcessedEvent {
    private Long shopOrderId;
    private Long paymentId;
    private BigDecimal paymentTotalPrice;
    private boolean isProduct;
    private boolean isService;
}
