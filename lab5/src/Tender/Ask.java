package Tender;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Ask extends Thread {
    private int idAsk;
    private int price;
    private CyclicBarrier barrier;

    //region Constructor and Getter
    public Ask(int idAsk, CyclicBarrier barrier) {
        this.idAsk = idAsk;
        this.barrier = barrier;
    }

    public int getIdAsk() {
        return idAsk;
    }
    public int getPrice() {
        return price;
    }
    //endregion

    @Override
    public void run() {
        try {
            price = new Random().nextInt(1000) ;
            System.out.println("Продавец " + this.getIdAsk() + " определил цену: " + price);

            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
