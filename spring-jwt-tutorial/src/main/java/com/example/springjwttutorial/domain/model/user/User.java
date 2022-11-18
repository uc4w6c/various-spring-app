package com.example.springjwttutorial.domain.model.user;

public record User(
    int id,
    String name,
    Coin coin) {

  public User useCoin(int pay){
    if(this.coin.canGacha(pay)){
      return new User(this.id, this.name, this.coin.sub(pay));
    } else {
      // TODO エラー処理
      throw new RuntimeException();
    }
  }

  public User buyCoin(int amt){
    return new User(this.id, this.name, this.coin.add(amt));
  }

  public int getCoin(){
    return this.coin.getAmt();
  }
}
