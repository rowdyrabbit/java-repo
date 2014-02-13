package dynamic_proxies;

import dynamic_proxies.caching.CacheProxy;
import dynamic_proxies.domain.Purchase;
import dynamic_proxies.service.PurchaseService;

import java.math.BigDecimal;

public class TestDynamicProxy {

    public static void main(String[] args) {
        PurchaseService service = (PurchaseService) CacheProxy.newInstance(new PurchaseService.PurchaseServiceImpl());

        Purchase purchase = buySomethingCool();
        service.confirmPurchase(purchase);
    }

    private static Purchase buySomethingCool() {
        return new Purchase("Sydney", "KitchenAid Deluxe Mixer", new BigDecimal(549));
    }
}
