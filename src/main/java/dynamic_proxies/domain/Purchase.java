package dynamic_proxies.domain;

import java.math.BigDecimal;

public class Purchase {

    private final String customerSuburb;
    private final String itemDescription;
    private final BigDecimal price;

    public Purchase(String customerSuburb, String itemDescription, BigDecimal price) {
        this.customerSuburb = customerSuburb;
        this.itemDescription = itemDescription;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCustomerSuburb() {
        return customerSuburb;
    }

    public String getItemDescription() {
        return itemDescription;
    }
}