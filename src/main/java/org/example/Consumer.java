package org.example;

import java.util.Random;

public class Consumer {
    public void pickup() {
        int quantity = new Random().nextInt(100) + 200;
        Warehouse.getInstance().getGoods(quantity);
    }
}
