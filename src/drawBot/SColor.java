package drawBot;

public class SColor {
	int id;
	int red;
	int green;
	int blue;
	int x;
	int y;
	public SColor(int id, int red, int green, int blue, int x, int y) {
		this.id = id;
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.x = x;
		this.y = y;
	}
	public int getID() {
		return id;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}
