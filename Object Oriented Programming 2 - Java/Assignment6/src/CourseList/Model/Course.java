package CourseList.Model;

/**
 * Model class for a Course object
 * Each Course object contains a course code, grade, and max grade
 * 
 * Didn't realize the class was already provided by the time I already made my own
 * Just merged some of the matching stuff into mine
 * 
 * @author Chris Yeung
 * @author Ming
 * @author snadi
 */
public class Course {
	
	private String courseCode;
	private double grade, maxGrade;
	
	public Course(String code){
		this.setCourseCode(courseCode);
		this.setGrade(0);
		this.setMaxGrade(0);
	}
	
	public Course(String courseCode, double grade, double maxGrade){
		this.setCourseCode(courseCode);
		this.setGrade(grade);
		this.setMaxGrade(maxGrade);
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
        if (courseCode != null && !courseCode.trim().isEmpty() && courseCode.matches("[a-zA-Z]{4}\\d{5}"))
            this.courseCode = courseCode;
        else
            throw new IllegalArgumentException("Course code can't be empty");
    }

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		if(grade < 0){
			System.out.println("Cannot enter negative grade");
			throw new IllegalArgumentException("Cannot enter negative grade");
		}
		this.grade = grade;
	}

	public double getMaxGrade() {
		return maxGrade;
	}

	public void setMaxGrade(double maxGrade) {
		if(grade < 0){
			System.out.println("Cannot enter negative grade");
			throw new IllegalArgumentException("Cannot enter negative grade");
		}
		this.maxGrade = maxGrade;
	}
	
    @Override
    public String toString() {
		return courseCode;
        //return String.format("Course Code: %s\nGrade: %.2f\nMax Grade: %.2f\n", courseCode,grade,maxGrade);
    }
	
	
}