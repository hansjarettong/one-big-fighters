import gifAnimation.*;
import ddf.minim.*;

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


void setup() {
  imageMode(CENTER);
  initialVal = 255; // initial value
  rateChange = 5; // rate of change
  staged = 1;

  size(868, 453);
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

void draw() {
  title();
  loading();
  fightscreen();
  tryagain();
}
