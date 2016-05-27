package m2dl.jlm.projetsma.agent;

import sma.EcoAgents.Student;
import sma.EcoAgents.Teacher;

public interface ICreateAgent {

    public Teacher createTeacherAgent(String id);

    public Student createStudentAgent(String id);

}
