package drawBot;

public class FinalResult {
	int x;
	int y;
	int rowNumber;
	int id;
	
	public FinalResult(int x, int y, int rowNumber, int id) {
		this.x = x;
		this.y = y;
		this.rowNumber = rowNumber;
		this.id = id;
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

	public int getRowNumber() {
		return rowNumber;
	}

	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
