import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class p2 {
//needs to have multiple classes
	
	public static Map map;
	public static Queue q;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("p2");
		Tile[][][] array = readMapBased("test1");
		map = new Map(array.length, array[0].length, array[0][0].length);
		q = new Queue();
		Tile start = null;
		for (int room = 0; room < array[0][0].length; room++) {
			for (int r = 0; r < array.length; r++) {
				for (int c = 0; c < array[0].length; c++) {
					Tile myTile = array[r][c][room];
					map.setEl(r, c, room, myTile);
					if (array[r][c][room].getType() == 'W') {
						start = map.getTile(r, c, room);
						System.out.println("start is at " + r + " " + c + " " + room);
					}
				}
			}
		}
//		System.out.println(map.getTile(3, 2, 0));
		map.setStart(start);
		System.out.println(map.getStart().toString());
//		System.out.println(queueHelper(map.getStart()));
		
	}
	
	//returns an array of the map which can then be put into the map class in main method
	public static Tile[][][] readMapBased(String filename) {
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			int rowIndex = 0;
			int roomIndex = 0;
			scanner.nextLine();
			
			Tile[][][] array = new Tile[numRows][numCols][numRooms];
			//process the map
			while (scanner.hasNext() && roomIndex < numRooms) {
				rowIndex = 0;
				while (scanner.hasNextLine() && rowIndex < numRows) {
					//grab a line (one row of the map)
					String row = scanner.nextLine();
					
					
					if (row.length()>0) {
						for (int i = 0; i < numCols && i < row.length(); i++) {
							//grabs each element and puts it in a tile
							char el = row.charAt(i);
							Tile obj = new Tile(rowIndex, i, roomIndex, el);
							array[rowIndex][i][roomIndex] = obj;
							
						}
						rowIndex++;
					}
					
					
				}
				roomIndex++;
			}
			return array;
			
		}catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}
	
	public static Tile[][][] readCoorBased(String filename){
		try {
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
			
			int numRows = scanner.nextInt();
			int numCols = scanner.nextInt();
			int numRooms = scanner.nextInt();
			int rowIndex = 0;
			int roomIndex = 0;
			scanner.nextLine();
			
			Tile[][][] array = new Tile[numRows][numCols][numRooms];
			
			while (scanner.hasNext()) {
				String obj = scanner.next();
				char type = obj.charAt(0);
				int row = scanner.nextInt();
				int col = scanner.nextInt();
				int room = scanner.nextInt();
				Tile tile = new Tile(row, col, room, type);
				array[row][col][room] = tile;
				
			}
			
			for (int room = 0; room < numRooms; room++) {
				for (int r = 0; r < numRows; r++) {
					for (int c = 0; c < numCols; c++) {
						if (array[r][c][room] == null) {
							array[r][c][room] = new Tile(r, c, room, '.');
						}
					}
				}
			}
		return array;
			
		}catch (FileNotFoundException e){
			System.out.println(e);
		}
		return null;
	}
	
	//throws exceptions, need to figure out why
	public static void queueSolve(Map maze) {
		maze = map;
		Tile start = maze.getStart();
		int room = start.getRoom();
		Queue<Tile> path = new Queue<Tile>(); //tracks the path later on
		Queue<Tile> q = new Queue<>(); //queues up tiles to be visited
		ArrayList<Tile> visited = new ArrayList<Tile>(); //tracks tiles that we already visited
	}
	

}
