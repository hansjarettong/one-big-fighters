class Stats {

  void draw_healthbars() {
    refresh_healthbars();
    rectMode(CORNERS);
    fill(255, 0, 0);
    if (player1.health()>0) {
      rect(width/2-width/20-width*2/5*player1.health()/100, height/8, width/2-width/20, height/14+height/10);
    }
    if (player2.health()>0) {
      rect(width/2+width/20, height/8, width/2+width/20+width*2/5*player2.health()/100, height/14+height/10);
    }
  }

  void refresh_healthbars() {
    rectMode(CORNERS);
    fill(117, 157, 136);
    rect(width/20, height/8, width/2-width/20, height/14+height/10);
    rect(width/2+width/20, height/8, width-width/20, height/14+height/10);
  }
}
