public class CourseDBElement implements Comparable<CourseDBElement> {
	public String CourseID;
	public int CRN;
	public int credits;
	public String roomNumber;
	public String instructorName;
	// my AP java teacher would cry looking at this
	
	public CourseDBElement(String c, int cr, int cred, String roomNum, String instr) {
		CourseID = c;
		CRN = cr;
		credits = cred;
		roomNumber = roomNum;
		instructorName = instr;
	}
	
	@Override
	public int compareTo(CourseDBElement other) {
        if (this == other) {
            return 0; // Objects are the same, return equality
        }
        
        // Compare each attribute
        if (!this.CourseID.equals(other.CourseID)) {
            return -1; // If CourseID is not equal
        }
        if (this.CRN != other.CRN) {
            return -1; // If CRN is not equal
        }
        if (this.credits != other.credits) {
            return -1; // If credits are not equal
        }
        if (!this.roomNumber.equals(other.roomNumber)) {
            return -1; // If roomNumber is not equal
        }
        if (!this.instructorName.equals(other.instructorName)) {
            return -1; // If instructorName is not equal
        }

        return 0; // All attributes are equal
    }
	
	public int hashCode() {
		String temp=Integer.toString(CRN);
		return temp.hashCode();
	}
	
	 // Getter for CourseID
    public String getID() {
        return CourseID;
    }
    
    // Getter for CRN
    public int getCRN() {
        return CRN;
    }
    
    // Getter for credits
    public int getCredits() {
        return credits;
    }
    
    // Getter for roomNumber
    public String getRoomNum() {
        return roomNumber;
    }
    
    // Getter for instructorName
    public String getInstructorName() {
        return instructorName;
    }
    
    public String toString() {
    	return String.format("Course:%s CRN:%d Credits:%d Instructor:%s Room:%s", 
    			CourseID, CRN, credits, instructorName, roomNumber);
    }
}
