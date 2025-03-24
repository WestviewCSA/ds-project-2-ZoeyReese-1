
public class Map {
	public Tile[][][] map; //2d isn't enough, what about # of rooms?
	private Tile start;
	
	public Tile[][][] getMap() {
		return map;
	}
	
	public Tile getStart() {
		return start;
	}
	
	public void setStart(Tile tile) {
		this.start = tile;
	}
	
	public Map(int rows, int cols, int rooms) {
		map = new Tile[rows][cols][rooms];
		start = getTile(0, 0, 0);
	}
	public Map(Tile[][][] map) {
		this.map = map;
	}
	
	public int getRows() {
		return map.length;
	}
	
	public int getCols() {
		return map[0].length;
	}
	
	public void setEl(int row, int col, int rooms, Tile el) {
		map[row][col][rooms] = el;
	}
	
	public Tile getTile(int row, int col, int rooms) {
		return map[row][col][rooms];
	}

	public void setMap(Tile[][][] array) {
		// TODO Auto-generated method stub
		map = array;
	}
	
}
