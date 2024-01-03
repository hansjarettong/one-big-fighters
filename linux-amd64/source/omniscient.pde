boolean someoneDied=false;

void check_what_happens_to_player_2() {
  //hit box
  if (abs(player1.xpos()-player2.xpos())<player1._size() && abs(player1.ypos()-player2.ypos())<player1._size()/2) {
    player2.isPunched();
    player2.reposition_x(player2.xpos()+player2._size()/3);
    player2.grow();
  }
  if (abs(player1.xpos()-player2.xpos())<player1._size()*3/2 && abs(player1.ypos()-player2.ypos())<player1._size()/2) {
    player2.isKicked();
    player2.reposition_x(player2.xpos()+player2._size()/2);
    player2.grow();
  }
}
void check_what_happens_to_player_1() {
  if (abs(player1.xpos()-player2.xpos())<player2._size()*3/2 && abs(player1.ypos()-player2.ypos())<player2._size()/2) {
    player1.isKicked();
    player1.reposition_x(player1.xpos()-player1._size()/2);
    player1.grow();
  }
  if (abs(player1.xpos()-player2.xpos())<player2._size() && abs(player1.ypos()-player2.ypos())<player2._size()/2) {
    player1.isPunched();
    player1.reposition_x(player1.xpos()-player1._size()/3);
    player1.grow();
  }
}





void collision() {
  if (abs(player1.xpos()-player2.xpos())<player1._size()/3+player2._size()/3) {
    player1.reposition_x(player2.xpos()-(player2._size()/3+player1._size()/3));

    player1.stop_xspeed();
    player2.stop_xspeed();
  }
  if (player1.xpos()<stage.leftwall()) {
    player1.stop_xspeed(); 
    player1.reposition_x(stage.leftwall());
  }
  if (player2.xpos()>stage.rightwall()) {
    player2.stop_xspeed(); 
    player2.reposition_x(stage.rightwall());
  }
}

void check_who_died() {
  if (player1.isAlive()) player1.move();
  else {
    player1.beDead();
    player2.VictoryPose();
    if (dto==false) {
      player.pause();
      player=minim.loadFile("victory.mp3");
      player.loop();
      idle1.stop();
      idle2.stop();
      lose1.play();
      win2.play();
      dto=true;
    }
    someoneDied=true;
  }
  if (player2.isAlive()) player2.move();
  else {
    player2.beDead();
    player1.VictoryPose();
    if (dto==false) {
      player.pause();
      player=minim.loadFile("victory.mp3");
      player.loop();
      idle1.stop();
      idle2.stop();
      lose2.play();
      win1.play();
      dto=true;
    }
    someoneDied=true;
  }
}


void keyPressed() {
  if (key=='w' && player1.midair()==false) {
    player1.isJumping(true);
    player1.reset_yspeed();
    jump1.play();
  }
  if (key=='a' && player1.right()==false) {
    player1.isMovingLeft(true);
    player1.reset_xspeed();
    backward1.play();
  }
  if (key=='d' && player1.left()==false) {
    player1.isMovingRight(true);
    player1.reset_xspeed();
    forward1.play();
  }
  if (key==CODED) {
    if (keyCode==UP && player2.midair()==false) {
      player2.isJumping(true);
      player2.reset_yspeed();
      jump2.play();
    }
    if (keyCode==LEFT && player2.right()==false) {
      player2.isMovingLeft(true);
      player2.reset_xspeed();
      walk2.play();
    }
    if (keyCode==RIGHT && player2.left()==false) {
      player2.isMovingRight(true);
      player2.reset_xspeed();
      walk2.play();
    }
  }
  if (key=='.' && player2.midpunch()==false && player2.midkick()==false && someoneDied==false) {
    player2.isPunching(true);
    player2.start_timer();
    punch2.play();
    punch.trigger();
  }
  if (key=='/' && player2.midpunch()==false && player2.midkick()==false && someoneDied==false) {
    player2.isKicking(true);
    player2.start_timer();
    kick2.play();
    kick.trigger();
  }

  if (key=='g' && player1.midpunch()==false && player1.midkick()==false && someoneDied==false) {
    player1.isPunching(true);
    player1.start_timer();
    punch1.play();
    punch.trigger();
  }
  if (key=='h' && player1.midpunch()==false && player1.midkick()==false && someoneDied==false) {
    player1.isKicking(true);
    player1.start_timer();
    kick1.play();
    kick.trigger();
  }
}

void keyReleased() {
  if (key=='a') {
    player1.isMovingLeft(false);
    backward1.stop();
  }
  if (key=='d') {
    player1.isMovingRight(false);
    forward1.stop();
  }
  if (key==CODED) {
    if (keyCode==LEFT) {
      player2.isMovingLeft(false);
      walk2.play();
    }
    if (keyCode==RIGHT) {
      player2.isMovingRight(false);
      walk2.play();
    }
  }
}


void mousePressed() {
  if (mouseY<height/2) again=true;
  if (mouseY>height/2) quit=true;
}

void title() {
  if (staged==1) {
    if (done_this_once==false) {
      player=minim.loadFile("title.mp3");
      player.loop();
      done_this_once=true;
    }
    background(startscreen);
    textAlign(CENTER);
    textSize(30);
    text("Press any key to start", 450, 300);     
    fill(255, 255, 255, initialVal);
    if ((initialVal == 0) || (initialVal == 255))
      rateChange = -rateChange;
    initialVal+= rateChange;

    if (keyPressed) {
      staged++;
      player.pause();
      done_this_once=false;
      player=minim.loadFile("loading.wav");
      player.loop();
    }
  }
}

void loading() {
  if (staged==2) {
    background(0);
    textSize(50); 
    text("Loading...", 450, 250);
    fill(255, 255, 255, initialVal);
    if ((initialVal == 0) || (initialVal == 255))
      rateChange = -rateChange;
    initialVal+= rateChange;
    timeload += 1;
    if (timeload>=0) {
      staged++;
      player.pause();
    }
  }
}

void fightscreen() {
  if (staged==3) {
    if (done_this_once==false) {
      player1=new Fighter(width/4, stage._floor(), width/8, 1);
      player2=new Fighter(width/4*3, stage._floor(), width/8, 2);
      fightscreen= loadImage(floor(random(4))+".jpg");
      player=minim.loadFile(floor(random(3))+".mp3");
      player.loop();
      count=0;
      someoneDied=false;
      done_this_once=true;
      dto=false;
    }
    background(fightscreen);
    if (someoneDied==false) {
      player1.move();
      player2.move();
    }
    collision();
    stats.draw_healthbars();
    check_who_died();
    if (someoneDied) {
      count+=1;
    }
    if (count>300) {
      staged++;
      done_this_once=false;
    }
  }
}

void tryagain() {
  if (staged==4) {
    image(tryAgain, width/2, height/2, width, height);
    if (done_this_once==false) {
      again=false;
      quit=false;
      done_this_once=true;
    }
    if (again) {
      player.pause();
      staged=2;
      done_this_once=false;
    }
    if (quit) {
      exit();
    }
  }
}
