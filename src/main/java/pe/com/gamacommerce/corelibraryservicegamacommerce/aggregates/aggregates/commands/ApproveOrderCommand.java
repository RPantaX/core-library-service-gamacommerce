package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.commands;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApproveOrderCommand {
    private Long shopOrderId;
    private BigDecimal paymentTotalPrice;
    private Boolean isProduct;
    private Boolean isService;
}
