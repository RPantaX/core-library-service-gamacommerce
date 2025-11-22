package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.commands;

import pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.requests.RequestProductsEvent;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReserveProductCommand {
    private Long shopOrderId;
    private List<RequestProductsEvent> requestProductsEventList;
}
