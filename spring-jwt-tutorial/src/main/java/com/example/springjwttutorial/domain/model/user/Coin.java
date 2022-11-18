package com.example.springjwttutorial.domain.model.user;

public record Coin(int amt) {
  /**
   * コインをマイナスします
   * @param sub
   * @return
   */
  public Coin sub(int sub){
    return new Coin(this.amt - sub);
  }

  /**
   * コインをプラスします
   * @param add
   * @return
   */
  public Coin add(int add){
    return new Coin(this.amt + add);
  }

  /**
   * 所持しているコインの数を返します
   * @return
   */
  public int getAmt(){
    return this.amt;
  }

  /**
   * ガチャが引けるかどうか判定します
   * @param need
   * @return
   */
  public boolean canGacha(int need){
    if(this.amt - need < 0){
      return false;
    }
    return true;
  }
}
