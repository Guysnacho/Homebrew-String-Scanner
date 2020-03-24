import java.util.*;
import java.util.regex.Pattern;
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
			//Make a temporary variable to hold the token
			String current = input.next();
			//Check if this is a comment
			if(current.contains("/*")){
				//If it is a comment, continue reading until we find a */
				while(!current.contains("*/")){
					current = input.next();
				}
				//This means we've found the closing comment brace. 
				//So we'll continue so we don't add the */ to the list
				continue;
			}

			//Check if it is a read/write
			if(current.equals("read") || current.equals("write")){
				list.add(current);
				continue;
			}

			//Check if its an arithmatic opperator
			if(current.equals("/")){
				list.add("div");
				continue;
			} else if(current.equals("*")){
				list.add("times");
				continue;
			} else if(current.equals("+")){
				list.add("plus");
				continue;
			} else if(current.equals("-")){
				list.add("minus");
				continue;
			}

			//Check if the token is a number.
			Pattern pattern = Pattern.compile("-?\\d+");
			if(pattern.matcher(current).matches()){
				list.add("number");
				continue;
			}

			//check if the token is an id
			pattern = Pattern.compile("\\D+");
			if(pattern.matcher(current).matches()){
				list.add("id");
				continue;
			}

			//If all of these fail then the mission has failed and we've been fed an invalid token.
			input.close();
			list.clear();
			return "error";
		}

		//Format the output
		String out = "(";
		for(String item: list){
			out = out + item + ",";
		}
		out = out.substring(0,out.length()-1) + ")";

		//Close the scanner
		input.close();
		return out;
	}

	//We need the FileNotFound exception in case the file to be read isn't in the same directory as this file
  public static void main(String[] args) throws FileNotFoundException {
		System.out.println(scan("foo.txt"));
  }
}