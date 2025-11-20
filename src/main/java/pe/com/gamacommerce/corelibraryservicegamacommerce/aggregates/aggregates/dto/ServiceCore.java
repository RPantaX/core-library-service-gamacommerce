package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ServiceCore {
    private Long serviceId;
    private BigDecimal price;
}
