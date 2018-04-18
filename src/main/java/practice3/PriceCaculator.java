package practice3;

import java.math.BigDecimal;

public class PriceCaculator {
    private final Order _order;
    private BigDecimal subTotal;

    public PriceCaculator(Order order, BigDecimal subTotal) {
        this._order = order;
        this.subTotal = subTotal;
    }

    public BigDecimal compute(){
        totalUpLineItems();
        subtractDiscounts();
        return subTotal.add(calculateTax());
    }

    private void totalUpLineItems() {
        for (OrderLineItem lineItem : _order.getOrderLineItemList()) {
            subTotal = subTotal.add(lineItem.getPrice());
        }
    }

    private void subtractDiscounts() {
        for (BigDecimal discount : _order.getDiscounts()) {
            subTotal = subTotal.subtract(discount);
        }
    }

    private BigDecimal calculateTax() {
        return subTotal.multiply(_order.getTax());
    }
}
