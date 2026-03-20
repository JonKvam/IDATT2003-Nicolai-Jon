package edu.ntnu.prog2.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {

  private final List<Share> shares;

  public Portfolio() {
    this.shares = new ArrayList<>();
  }

  public boolean addShare(Share share) {
    validateShare(share);
    if (shares.contains(share)) {
      throw new IllegalArgumentException("share already exists in portfolio");
    }
    return shares.add(share);
  }

  public boolean removeShare(Share share) {
    validateShare(share);
    if (!shares.contains(share)) {
      throw new IllegalArgumentException("share does not exist in portfolio");
    }
    return shares.remove(share);
  }

  public List<Share> getShares() {
    return List.copyOf(shares);
  }

  public boolean contains(Share share) {
    validateShare(share);
    return shares.contains(share);
  }

  private void validateShare(Share share) {
    if (share == null) {
      throw new IllegalArgumentException("share cannot be null");
    }
  }

}
