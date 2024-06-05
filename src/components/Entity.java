package src.components;

public abstract class Entity extends IconAnimation {
  protected int xPosition;
  protected int yPosition;

  protected Entity(String[] imagePaths, int initialX, int initialY, int width, int height) {
    super(imagePaths, width, height);
    this.xPosition = initialX;
    this.yPosition = initialY;
    this.width = width;
    this.height = height;
    setEntityBounds(xPosition, yPosition, width, height);
  }

  private void setEntityBounds(int initialX, int initialY, int width, int height) {
    setBounds(initialX, initialY, width, height);
  }

  public int getXPosition() {
    return xPosition;
  }

  public void setXPosition(int xPosition) {
    this.xPosition = xPosition;
    setLocation(xPosition, yPosition);
    repaint();
  }

  public int getYPosition() {
    return yPosition;
  }

  public void setYPosition(int yPosition) {
    this.yPosition = yPosition;
    setLocation(xPosition, yPosition);
    repaint();
  }
}
