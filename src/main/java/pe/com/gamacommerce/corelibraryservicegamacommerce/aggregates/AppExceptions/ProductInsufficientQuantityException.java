package pe.com.gamacommerce.corelibraryservicegamacommerce.aggregates.AppExceptions;

import lombok.Getter;

@Getter
public class ProductInsufficientQuantityException  extends RuntimeException{
    private final Long itemProductId;
    private final Long shopOrderId;

    public ProductInsufficientQuantityException(Long itemProductId, Long shopOrderId) {
        super("Insufficient quantity for product with id " + itemProductId + " in order with id " + shopOrderId);
        this.itemProductId = itemProductId;
        this.shopOrderId = shopOrderId;
    }

}
