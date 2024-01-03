/* autogenerated by Processing revision 1293 on 2024-01-03 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import gifAnimation.*;
import ddf.minim.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class obf extends PApplet {




PImage startscreen;

PFont title;
int screensizex, screensizey, staged;
int initialVal, rateChange; // fade effect
Stage stage;
Fighter player1;
Fighter player2;
Stats stats;
int timeload=1;
PImage fightscreen;
int counter;
float r;
float randomSprite1, randomSprite2;
boolean done_this_once=false;
boolean dto=false;
Gif jump1, jump2, forward1, backward1, walk2, idle1, idle2;
Gif kick1, kick2, punch1, punch2;
Gif win1, win2, lose1, lose2;
Minim minim;
AudioPlayer player;
AudioSample kick;
AudioSample punch;
PImage tryAgain;
boolean again=false;
boolean quit=false;
int count;


public void setup() {
  imageMode(CENTER);
  initialVal = 255; // initial value
  rateChange = 5; // rate of change
  staged = 1;

  /* size commented out by preprocessor */;
  startscreen= loadImage("bluelegend.jpg");

  image(startscreen, 0, 0);
  title= createFont("font", 1000, true);
  stage=new Stage(width, height);
  stats=new Stats();
  jump1=new Gif(this, "sprites/ryu/jump.gif");
  forward1=new Gif(this, "sprites/ryu/forward.gif");
  backward1=new Gif(this, "sprites/ryu/backward.gif");
  idle1=new Gif(this, "sprites/ryu/idle.gif");
  punch1=new Gif(this, "sprites/ryu/punch.gif");
  kick1= new Gif(this, "sprites/ryu/kick.gif");
  jump2=new Gif(this, "sprites/sagat/jump.gif");
  walk2=new Gif(this, "sprites/sagat/walk.gif");
  idle2=new Gif(this, "sprites/sagat/idle.gif");
  punch2=new Gif(this, "sprites/sagat/punch.gif");
  kick2=new Gif(this, "sprites/sagat/kick.gif");
  win1=new Gif(this, "sprites/ryu/victory.gif");
  win2=new Gif(this, "sprites/sagat/victory.gif");
  lose1=new Gif(this, "sprites/ryu/defeat.gif");
  lose2=new Gif(this, "sprites/sagat/defeat.gif");
  minim = new Minim(this);
  kick=minim.loadSample("kick.mp3");
  punch=minim.loadSample("punch.mp3");
  tryAgain=loadImage("try.jpg");
}

public void draw() {
  title();
  loading();
  fightscreen();
  tryagain();
}
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
    gravity=0.8f;

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


  public void attack() {
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


  public void start_timer() {    
    timer=millis();
  }


  public float time_ellapsed() {
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
    size*=1.09f;
  }
}
boolean someoneDied=false;

public void check_what_happens_to_player_2() {
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
public void check_what_happens_to_player_1() {
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





public void collision() {
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

public void check_who_died() {
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


public void keyPressed() {
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

public void keyReleased() {
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


public void mousePressed() {
  if (mouseY<height/2) again=true;
  if (mouseY>height/2) quit=true;
}

public void title() {
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

public void loading() {
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

public void fightscreen() {
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

public void tryagain() {
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
class Stage {
  float floor_coordinates;
  float leftwall_coordinates=0;
  float rightwall_coordinates;

  Stage(float w, float h) {
    floor_coordinates=h*4/5;
    rightwall_coordinates=width;
    leftwall_coordinates=0;
  }

  public float _floor() {
    return floor_coordinates;
  }  

  public float leftwall() {
    return leftwall_coordinates;
  }
  public float rightwall() {
    return rightwall_coordinates;
  }
}
class Stats {

  public void draw_healthbars() {
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

  public void refresh_healthbars() {
    rectMode(CORNERS);
    fill(117, 157, 136);
    rect(width/20, height/8, width/2-width/20, height/14+height/10);
    rect(width/2+width/20, height/8, width-width/20, height/14+height/10);
  }
}


  public void settings() { size(868, 453); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "obf" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}