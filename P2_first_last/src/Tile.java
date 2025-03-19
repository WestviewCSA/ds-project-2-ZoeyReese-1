
public class Tile {
	private int row, col, room;
	private char type; //@ $ W .
	public boolean visited;
	
	public Tile(int row, int col, int room, char type) {
		super();
		this.row = row;
		this.col = col;
		this.room = room;
		this.type = type;
		visited = false;
	}
	
	public Tile(Tile tile) {
		row = tile.getRow();
		col = tile.getCol();
		room = tile.getRoom();
		type = tile.getType();
		visited = false;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public void visited() {
		visited = true;
	}
	
	public int getRow() {
		return row;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getRoom() {
		return room;
	}
	
	public void setRoom(int room) {
		this.room = room;
	}
	
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	public String toString() {
		return "{" + row + ", " + col + ", " + room + ", " + type + "}";
	}

	
}
