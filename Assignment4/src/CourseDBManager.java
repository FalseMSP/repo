import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseDBManager implements CourseDBManagerInterface {
	CourseDBStructure struct;
	public CourseDBManager() {
		struct = new CourseDBStructure(10);
	}
	
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		struct.add(element);
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return struct.get(crn);
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(input);
		int cred, crn;
		CourseDBElement cde;
		String courses;
		String[] course;
		while (in.hasNextLine()) {
			courses=in.nextLine();
			course=courses.split(" ", 5);
			crn=Integer.parseInt(course[1]);
			cred=Integer.parseInt(course[2]);
			cde=new CourseDBElement(course[0], crn, cred, course[3], course[4]);
			struct.add(cde);
		}
	}

	@Override
	public ArrayList<String> showAll() {
		return struct.showAll();
	}

}
