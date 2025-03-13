
public class Map {
	public Tile[][][] map; //2d isn't enough, what about # of rooms?
	
	public Tile[][][] getMap() {
		return map;
	}

	public Map(int rows, int cols, int rooms) {
		map = new Tile[rows][cols][rooms];
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
