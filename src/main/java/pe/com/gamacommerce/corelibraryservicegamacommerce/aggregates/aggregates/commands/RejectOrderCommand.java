package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.aggregates.commands;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RejectOrderCommand {
    private Long shopOrderId;
}
