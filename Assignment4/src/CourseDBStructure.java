import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math.*;

public class CourseDBStructure implements CourseDBStructureInterface {
	private LinkedList[] hashTable;
    private int tableSize;
    private int size;
    // isPrime needed for size 
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    public CourseDBStructure(int size) {
	for (int i = (int) Math.floor((size*1.0)/1.5); i < i*i; i++) {
	    if (i % 4 == 3 && isPrime(i)) {
		tableSize = i;
		break;
	    }
	}
    	hashTable = new LinkedList[tableSize];
    }
    
    public CourseDBStructure(String testing,int size) {
    	tableSize=size;
		hashTable=new LinkedList[tableSize];
	}
    
	@Override
	public void add(CourseDBElement element) {
        int hashVal = Math.abs(element.hashCode()) % tableSize;
        if (hashTable[hashVal] == null) {
            hashTable[hashVal] = new LinkedList<CourseDBElement>();
        }

        LinkedList<CourseDBElement> temp = hashTable[hashVal];
        for (CourseDBElement e : temp) {
            if (e.getCRN() == element.getCRN()) {
                // Update the element if it already exists
                temp.remove(e);
                break;
            }
        }

        temp.add(element);
        size++;
    }

	@Override
	public CourseDBElement get(int crn) throws IOException {
		String temp = Integer.toString(crn);
		int code = Math.abs(temp.hashCode())% tableSize;
		if(hashTable[code]==null) {
			throw new IOException();
		} else {
			return (CourseDBElement) hashTable[code].get(0);
		}
	}

	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> courses=new ArrayList<String>();
		
		for(int i=0;i<tableSize;i++) {
			if (hashTable[i]!=null) {
				for(int j=0;j<hashTable[i].size();j++) {
					CourseDBElement element = (CourseDBElement) hashTable[i].get(j);
					courses.add("\n"+element.toString());
				}
			}
		}
		return courses;
	}

	@Override
	public int getTableSize() {
		// TODO Auto-generated method stub
		return tableSize;
	}

}
