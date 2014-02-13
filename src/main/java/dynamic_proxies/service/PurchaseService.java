package dynamic_proxies.service;

import dynamic_proxies.domain.Purchase;

public interface PurchaseService {

    public boolean confirmPurchase(Purchase purchase);

    class PurchaseServiceImpl implements PurchaseService {

        @Override
        public boolean confirmPurchase(Purchase purchase) {
            //Go send payment off to payment system.
            return true;
        }
    }
}
