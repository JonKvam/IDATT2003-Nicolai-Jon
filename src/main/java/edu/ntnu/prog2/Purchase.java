package edu.ntnu.prog2;

public class Purchase extends Transaction{
    public Purchase(Share share, int week){
        super(share, week, new PurchaseCalculator(share))
    }

    @Override
    public void commit(Player player) {
        if (isCommitted()) {
            throw new IllegalStateException("Transaction already committed");
        }
        setCommitted(true);
    }
}
