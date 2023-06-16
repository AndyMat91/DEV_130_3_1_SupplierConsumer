package org.example;

public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        main.startWarehouse();
    }

    public void startWarehouse() {
        for (int i = 0; i < 3; i++) {
            Thread supp = new Thread(() -> {
                while (true) {
                    Supplier supplier = new Supplier();
                    supplier.delivery();
                }
            });
            supp.setDaemon(true);
            supp.start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Consumer consumer = new Consumer();
                consumer.pickup();
            }).start();
        }
    }
}