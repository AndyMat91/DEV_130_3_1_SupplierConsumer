package org.example;

import java.util.Random;

public class Warehouse {
    private static Warehouse warehouse;
    private int goods = 1000;

    private Warehouse() {
    }

    public static Warehouse getInstance() {
        if (warehouse == null) warehouse = new Warehouse();
        return warehouse;
    }

    public synchronized void getGoods(int quantity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Потребитель пришел на склад");
        while (goods < quantity) {
            try {
                System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Потребитель уснул в ожидании товара");
                wait();
                System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Потребитель проснулся после ожидания товара");
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
        goods -= quantity;
        System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Потребитель забрал " + quantity + " единиц товара." +
                "Остаток товара " + goods + " единиц. Потребитель уснул.");
        try {
            wait(new Random().nextInt(500));
            System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Потребитель проснулся и готов снова идти на склад");
        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        notify();
    }

    public synchronized void putGoods(int quantity) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Поставщик приехал на склад");
        goods += quantity;
        System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Поставщик закончил доставку. Остаток товара на складе: " +
                goods + " единиц. Поставщик поехал за новой партией товара (уснул).");
        try {
            wait(new Random().nextInt(500));
            System.out.println("ThreadId_" + Thread.currentThread().getId() + ": Поставщик готов выехать на склад (проснулся)");

        } catch (InterruptedException e) {
            System.out.println("Exception: " + e.getMessage());
        }
        notify();
    }
}
