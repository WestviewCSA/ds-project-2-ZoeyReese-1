
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
	
	public boolean canWalk(int row, int col, int room) {
		if (row < 0 || row >= getRows() || col < 0 || col >= getCols()) {
			return false;
		}//checks if the coordinates are in boundaries/would cause an error
		
		Tile tile = getTile(row, col, room); //gets tile if it exists
		return tile.getType() == '.' || tile.getType() == '$' || tile.getType() == '|';
		//if the tile equals any of the desired types then it returns true
	}
	
	public void printRoom(int room) {
		for (int i = 0; i < getRows(); i++) {
			for (int j = 0; j < getCols(); j++) {
				System.out.println(getTile(i, j, room).getType());
				//prints out all the tiles of a given room
			}
			System.out.println();
		}
	}
	
}
