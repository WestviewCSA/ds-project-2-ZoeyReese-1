import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class p2 {
//needs to have multiple classes
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("p2");
		Tile[][][] array = readMap("TEST01");
		Map myMap = new Map(array.length, array[0].length, array[0][0].length);
		myMap.setMap(array);
		int room = 0; //need to figure out how to update room
		for (int r = 0; r < array.length; r++) {
			for (int c = 0; c < array[0].length; c++) {
				myMap.setEl(r, c, room, array[r][c][room]);
			}
		}
		System.out.println(myMap.getTile(1, 3, 0));
		
	}
	
	//returns an array of the map which can then be put into the map class in main method
	public static Tile[][][] readMap(String filename) {
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
			while (scanner.hasNextLine() && rowIndex < numRows) {
				//grab a line (one row of the map)
				String row = scanner.nextLine();
				
				
				if (row.length()>0) {
					for (int i = 0; i < numCols && i < row.length(); i++) {
						//grabs each element and puts it in a tile
						char el = row.charAt(i);
						Tile obj = new Tile(rowIndex, i, el);
						array[rowIndex][i][roomIndex] = obj;
						
					}
					rowIndex++;
				}
				
				
			}
			return array;
			
		}catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return null;
	}

}
