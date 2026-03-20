package edu.ntnu.prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

  private final List<Share> shares;

  public Portfolio() {
    this.shares = new ArrayList<>();
  }

  public boolean addShare(Share share) {
    return shares.add(share);
  }

  public boolean removeShare(Share share) {
    return shares.remove(share);
  }

  public List<Share> getShares() {
    return shares;
  }

  public boolean contains(Share share) {
    return shares.contains(share);
  }

}
