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
