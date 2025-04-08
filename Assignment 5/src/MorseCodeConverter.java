import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MorseCodeConverter {
	public static MorseCodeTree a;
	
	public static String convertToEnglish(String s) {
		a = new MorseCodeTree();
		a.buildTree();
		String[] b = s.split(" ");
		String res = "";
		for (String i : b) { // wow my variable naming is horrible
			if (i.equals("/")) {
				res += " ";
				continue;
			}
			res += a.fetch(i);
		}
		return res;
	}
	
	public static String convertToEnglish(File a) throws FileNotFoundException {
		Scanner myReader;
		myReader = new Scanner(a);
		String temp = "";
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			temp += data;
		}
		return convertToEnglish(temp);
	}
	
	public static String printTree() {
		a = new MorseCodeTree();
		a.buildTree();
		ArrayList<String> temp = a.toArrayList();
		String res = "";
		for (String i : temp) {
			res += i + " ";
		}
		res = res.stripTrailing();
		return res;
	}
}
