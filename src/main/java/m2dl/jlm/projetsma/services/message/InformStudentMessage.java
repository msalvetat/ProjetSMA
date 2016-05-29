package m2dl.jlm.projetsma.services.message;

import m2dl.jlm.projetsma.environment.Course;

public class InformStudentMessage extends AbstractMessage {

    private Course course;

    public InformStudentMessage(Course course) {
        super();
        this.course = course;
    }
    
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
