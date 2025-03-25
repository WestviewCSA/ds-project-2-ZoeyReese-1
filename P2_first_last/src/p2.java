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
		long startTime = System.nanoTime();
		Tile[][][] array = readMaze("test1");
		map = new Map(array.length, array[0].length, array[0][0].length);
		q = new Queue<Tile>();
		Tile start = null;
		Queue<Tile> starts = new Queue<Tile>(); // outside of class bc for some reason it wasn't populating right
		for (int room = 0; room < array[0][0].length; room++) {
			for (int r = 0; r < array.length; r++) {
				for (int c = 0; c < array[0].length; c++) {
					Tile myTile = array[r][c][room];
					map.setEl(r, c, room, myTile);
					if (array[r][c][room].getType() == 'W') {
						start = map.getTile(r, c, room);
						starts.enqueue(start);; //should add it to the start queue
					}
				}
			}
		}
		
		queueSolve(map, starts);
		optimalSolve(map, starts);
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Runtime: " + duration + " nanoseconds");
		
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
	
	public static Tile[][][] readMaze(String filename) {
	    //method to determine if its map based or coor based
		try {
	        File file = new File(filename);
	        Scanner scanner = new Scanner(file);

	        // Ensure first three integers exist
	        if (!scanner.hasNextInt()) {
	            System.out.println("Error: Invalid file format (Missing dimensions)");
	            scanner.close();
	            return null;
	        }
	        
	        // Read the three integers
	        int numRows = scanner.nextInt();
	        int numCols = scanner.nextInt();
	        int numRooms = scanner.nextInt();
	        scanner.nextLine(); // Move to the next line

	        // Peek at the next line without consuming it
	        if (!scanner.hasNextLine()) {
	            System.out.println("Error: File does not contain valid maze data.");
	            scanner.close();
	            return null;
	        }

	        String firstLine = scanner.nextLine(); // Read first non-empty line
	        scanner.close(); // Now safe to close

	        // Decide which method to call based on line format
	        if (firstLine.length() == numCols) {
	            return readMapBased(filename);  
	        } else {
	            return readCoorBased(filename); 
	        }

	    } catch (FileNotFoundException e) {
	        System.out.println("Error: File not found - " + filename);
	    }
	    return null;
	}
	
	public static void optimalSolve(Map maze, Queue<Tile> starts) {
		System.out.println("optimal");
		System.out.println("init starts" + starts);
		while (!starts.empty()) {
			Tile start = starts.dequeue();
			int room = start.getRoom();
			queueSolveRoom(maze, start);
		}
	}
	//optimal solve is the same as the queue solve
	
	
	public static void queueSolve(Map maze, Queue<Tile> starts) {
		System.out.println("queue");
		Queue<Tile> startsCopy = copyQueue(starts);
		System.out.println("init startscopy" + startsCopy);
		System.out.println("init starts" + starts);
		
		while (!startsCopy.empty()) {
			Tile start = startsCopy.peek();
			starts.enqueue(startsCopy.dequeue());
			queueSolveRoom(maze, start);
		}
		System.out.println(starts);
		System.out.println(startsCopy);
	}
	
	//solves 1 room. not good if there's multiple rooms/doorways
	public static void queueSolveRoom(Map maze, Tile start) {
		maze = map;
		Tile end = null; //last tile, we don't know yet
		int room = start.getRoom();
		Queue<Tile> path = new Queue<Tile>(); //tracks the path later on
		Queue<Tile> q = new Queue<>(); //queues up tiles to be visited
		ArrayList<Tile> visited = new ArrayList<Tile>(); //tracks tiles that we already visited
	
		q.enqueue(start); //first tile to be checked
		
		Tile[][] prev = new Tile[maze.getRows()][maze.getCols()];
		//holds the previous tiles that lead to the next tile
		
		while (!q.empty()) { //checks that queue still has elements
			Tile curr = q.dequeue(); //gets next tile in line
			visited.add(curr); //marks that we've visited this tile
			
			if (curr.getType() == '$' || curr.getType() == '|') {
				end = curr; //the last tile is the one that we want
				break; //if we reached the goal, then we can stop checking
			}
			
			//processes all valid neighbors
			int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
			//holds the ways to reach the north south east and west
			
			for (int[] dir : directions) {
				int newRow = curr.getRow() + dir[0];
				int newCol = curr.getCol() + dir[1];
				//gets coordinates of the neighbor
				
				if (maze.canWalk(newRow, newCol, room)) {
					Tile next = maze.getTile(newRow, newCol, room);
					if ((next.getType() == '.' || next.getType() == '$' || next.getType() == '|') && !containsTile(visited, next)) {
						q.enqueue(next);
						visited.add(next);
						prev[newRow][newCol] = curr; //the previous of the next tile == the curr tile
					}
				}
			}
		}
		
		if (end != null) {
			Tile backtrack = end;
			while (backtrack != start) {
				path.enqueue(backtrack);
				//creates the path
				backtrack = prev[backtrack.getRow()][backtrack.getCol()];
				//backtrack now refers to the prev so it can keep backtracking
			}
			path.dequeue(); //dequeues end tile, keeps its type
		
			//outlines the path
			while (!path.empty()) {
				Tile newTile = path.dequeue();
				newTile.setType('+');
				maze.setEl(newTile.getRow(), newTile.getCol(), room, newTile);
				//changes the types to show the path
			}
			maze.getTile(end.getRow(), end.getCol(), room).setType(end.getType());
			
			maze.printRoom(room);
		}else if (end == null) {
			System.out.println("Error: Diamond Wolverine Buck not found");
		}
		
		
	}
	
	public static void stackSolveRoom(Map maze, Tile start) {
		Tile end = null;
		int room = start.getRoom();
		Queue<Tile> path = new Queue<Tile>(); //tracks the path later on
		Queue<Tile> q = new Queue<>(); //queues up tiles to be visited
		ArrayList<Tile> visited = new ArrayList<Tile>(); //tracks tiles that we already visited
		
		
		
	}
	
	public static boolean containsTile(ArrayList<Tile> list, Tile tile) {
		for (Tile thisTile : list) { //checks each tile in the list
			if (thisTile.getRow() == tile.getRow() && thisTile.getCol() == tile.getCol()) {
				return true; //if they reference the same tile
			}
		}
		return false;
	}
	
	public static Queue<Tile> copyQueue(Queue<Tile> init){
		Queue<Tile> copy = new Queue<Tile>();
		Queue<Tile> temp = new Queue<Tile>();
		
		while (!init.empty()) {
			copy.enqueue(init.peek());
			temp.enqueue(init.dequeue());
		}
		
		while (!temp.empty()) {
			init.enqueue(temp.dequeue());
		}
		
		return copy;
	}
	
}
