package m2dl.jlm.projetsma.agent.student.knowledge;

import java.util.List;

import m2dl.jlm.projetsma.environment.Course;
import m2dl.jlm.projetsma.services.message.InformStudentMessage;

public interface IKnowledgeStudent {

    public List<InformStudentMessage> getInformStudentMessages();
    
    public void setInformStudentMessages(List<InformStudentMessage> informStudentMessages);
    
    public List<Course> getCourses();
    
    public void setCourses(List<Course> courses);
}
