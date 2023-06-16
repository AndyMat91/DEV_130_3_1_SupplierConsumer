package org.example;

import java.util.Random;

public class Supplier {
    public void delivery() {
        int quantity = new Random().nextInt(100) + 200;
        Warehouse.getInstance().putGoods(quantity);
    }
}
