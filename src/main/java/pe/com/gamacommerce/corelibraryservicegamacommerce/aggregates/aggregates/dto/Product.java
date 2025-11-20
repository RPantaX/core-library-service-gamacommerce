package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {
    private Long productId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    public Product(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
