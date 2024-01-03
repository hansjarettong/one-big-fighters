class Fighter {
  float xpos;
  float ypos;
  float xspeed;
  float yspeed;
  float gravity;
  float size;
  boolean isJumping;
  boolean isMovingRight;
  boolean isMovingLeft;
  boolean isPunching;
  boolean isKicking;
  boolean isIdle;
  float int_yspeed;
  float int_xspeed;
  float health;
  float timer;
  float int_xpos;
  float int_ypos;
  float int_size;
  int player_number;

  Fighter(float x, float y, float s, int pn) {
    xpos=x;
    ypos=y;
    size=s;

    xspeed=3;
    yspeed=15;
    gravity=0.8;

    isJumping=false;
    isMovingRight=false;
    isMovingLeft=false;
    isIdle=true;

    int_yspeed=yspeed;
    int_xspeed=xspeed;

    int_xpos=xpos;
    int_ypos=ypos;

    health=100;

    player_number=pn;
  }

  public void move() {

    if (isJumping && someoneDied==false) {
      if (player_number==1 && isPunching==false && isKicking==false) image(jump1, xpos, ypos, size, size);
      if (player_number==2 && isPunching==false && isKicking==false) image(jump2, xpos, ypos, size, size);
      ypos-=yspeed;
      yspeed-=gravity;
      if (ypos>stage._floor()) {
        ypos=stage._floor();
        yspeed=0;
        isJumping=false;
        if (player_number==1) jump1.stop();
        if (player_number==2) jump2.stop();
      }
    }
    if (isMovingLeft && isPunching==false && isKicking==false && someoneDied==false) {
      xpos-=xspeed;
      if (player_number==1 && isJumping==false && isPunching==false && isKicking==false && someoneDied==false) image(backward1, xpos, ypos, size, size);
      if (player_number==2 && isJumping==false && isPunching==false && isKicking==false && someoneDied==false) image(walk2, xpos, ypos, size, size);
    }
    if (isMovingRight && isPunching==false && isKicking==false && someoneDied==false) {
      xpos+=xspeed;
      if (player_number==1 && isJumping==false && isPunching==false && isKicking==false && someoneDied==false) image(forward1, xpos, ypos, size, size);
      if (player_number==2 && isJumping==false && isPunching==false && isKicking==false && someoneDied==false) image(walk2, xpos, ypos, size, size);
    }
    attack();


    if (player_number==1 && isJumping==false   && isKicking==false   && isMovingLeft==false    && isMovingRight==false   && isPunching==false && someoneDied==false) {
      idle1.play();
      image(idle1, xpos, ypos, size, size);
    } else if (isJumping||isKicking||isMovingLeft||isMovingRight||isPunching) idle1.stop();

    if (player_number==2 && isJumping==false   && isKicking==false   && isMovingLeft==false    && isMovingRight==false   && isPunching==false && someoneDied==false) {
      idle2.play();
      image(idle2, xpos, ypos, size, size);
    } else if (isJumping||isKicking||isMovingLeft||isMovingRight||isPunching) idle2.stop();
  }


  void attack() {
    if (isPunching) {
      if (player_number==1 && someoneDied==false) image(punch1, xpos, ypos, size, size);
      if (player_number==2 && someoneDied==false) image(punch2, xpos, ypos, size, size);

      if (time_ellapsed()>250) {
        isPunching=false;
        if (player_number==1) {
          check_what_happens_to_player_2();
          punch1.stop();
        }
        if (player_number==2) {
          check_what_happens_to_player_1();
          punch2.stop();
        }
      }
    }
    if (isKicking) {

      if (player_number==1) image(kick1, xpos, ypos, size, size);
      if (player_number==2) image(kick2, xpos, ypos, size, size);

      if (time_ellapsed()>500) {
        isKicking=false;
        if (player_number==1) {
          check_what_happens_to_player_2();
          kick1.stop();
        }
        if (player_number==2) {
          check_what_happens_to_player_1();
          kick2.stop();
        }
      }
    }
  }

  public float health() {
    return health;
  }
  public float xpos() {
    return xpos;
  }


  public float ypos() {
    return ypos;
  }

  public float _size() {
    return size;
  }
  public void reposition_x(float pos) {
    xpos=pos;
  }


  public void isJumping(boolean x) {
    isJumping=x;
  }

  public void isMovingLeft(boolean x) {
    isMovingLeft=x;
  }
  public void isMovingRight(boolean x) {
    isMovingRight=x;
  }

  public void isPunching(boolean x) {
    isPunching=x;
  }

  public void isKicking(boolean x) {
    isKicking=x;
  }

  public boolean midair() {
    return isJumping;
  }

  public boolean midpunch() {
    return isPunching;
  }


  public boolean midkick() {
    return isKicking;
  }

  public boolean left() {
    return isMovingLeft;
  }

  public boolean right() {
    return isMovingRight;
  }
  public void stop_xspeed() {
    xspeed=0;
  }

  public void reset_xspeed() {
    xspeed=int_xspeed;
  }
  public void reset_yspeed() {
    yspeed=int_yspeed;
  }



  public void isPunched() {
    health-=5;
  }

  public void isKicked() {
    health-=10;
  }


  void start_timer() {    
    timer=millis();
  }


  float time_ellapsed() {
    return millis()-timer;
  }

  public boolean isAlive() {
    if (health<=0) return false;
    else return true;
  }

  public void beDead() {
    if (isAlive()==false) {
      if (player_number==1) image(lose1, xpos, ypos, size, size);
      if (player_number==2) image(lose2, xpos, ypos, size, size);
    }
  }

  public void VictoryPose() {
    if (player_number==1) image(win1, xpos, ypos, size, size);
    if (player_number==2) image(win2, xpos, ypos, size, size);
  }

  public void grow() {
    size*=1.09;
  }
}
