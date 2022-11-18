package com.example.springjwttutorial.domain.model.user;

import com.example.springjwttutorial.application.exception.NotEnoughException;

public record User(
    int id,
    String name,
    Coin coin) {

  public User useCoin(int pay){
    if(this.coin.canGacha(pay)){
      return new User(this.id, this.name, this.coin.sub(pay));
    } else {
      throw new NotEnoughException();
    }
  }

  public User buyCoin(int amt){
    return new User(this.id, this.name, this.coin.add(amt));
  }

  public int getCoin(){
    return this.coin.getAmt();
  }
}
