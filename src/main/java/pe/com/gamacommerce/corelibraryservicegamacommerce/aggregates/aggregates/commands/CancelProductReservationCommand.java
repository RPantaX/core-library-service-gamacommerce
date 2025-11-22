package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.commands;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.dto.Product;
import lombok.*;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CancelProductReservationCommand {
    private Long shopOrderId;
    private List<Product> productList;
}
