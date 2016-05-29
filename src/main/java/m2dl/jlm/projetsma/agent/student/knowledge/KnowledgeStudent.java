package m2dl.jlm.projetsma.agent.student.knowledge;

import java.util.ArrayList;
import java.util.List;

import m2dl.jlm.projetsma.environment.Course;
import m2dl.jlm.projetsma.services.message.InformStudentMessage;

public class KnowledgeStudent implements IKnowledgeStudent {
    
    private List<InformStudentMessage> informStudentMessages = new ArrayList<>();
    
    private List<Course> courses = new ArrayList<>();

    public List<InformStudentMessage> getInformStudentMessages() {
        return informStudentMessages;
    }

    public void setInformStudentMessages(List<InformStudentMessage> informStudentMessages) {
        this.informStudentMessages = informStudentMessages;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

