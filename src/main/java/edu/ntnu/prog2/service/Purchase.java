package edu.ntnu.prog2.service;

import edu.ntnu.prog2.calculator.PurchaseCalculator;
import edu.ntnu.prog2.model.Player;
import edu.ntnu.prog2.model.Share;
import edu.ntnu.prog2.model.Transaction;

public class Purchase extends Transaction {
    public Purchase(Share share, int week){
        super(share, week, new PurchaseCalculator(share));
    }

    @Override
    public void commit(Player player) {
        validateCommit(player);
        setCommitted(true);
    }
}
