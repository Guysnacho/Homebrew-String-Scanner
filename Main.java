import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

class Main {
	public static String scan(String filename) throws FileNotFoundException {
		//make a new scanner to read the file and our List
		ArrayList<String> list = new ArrayList<>();
		File text = new File(filename);
		Scanner input = new Scanner(text);
		
		//Stops the loop when there aren't any characters left
		while(input.hasNext()){
			//Push the current "Token" to the list
			list.add(input.next());
			//Check if this is a comment
		}

		//Format the output
		String out = "(";
		for(String item: list){
			out = out + item + ",";
		}
		out = out.substring(0,out.length()-1) + ")";
		
		return out;
	}
  public static void main(String[] args) throws FileNotFoundException {
		System.out.println(scan("foo.txt"));
  }
}