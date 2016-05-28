package m2dl.jlm.projetsma.agent.impl;

import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.agent.impl.student.EcoStudentImpl;
import m2dl.jlm.projetsma.agent.impl.teacher.EcoTeacherImpl;
import m2dl.jlm.projetsma.agent.knowledge.EcoKnowledgeImpl;
import sma.EcoAgents;
import sma.agent.EcoStudent;
import sma.agent.EcoTeacher;
import sma.knowledge.EcoKnowledge;

public class EcoAgentsImpl extends EcoAgents {

    @Override
    protected ICreateAgent make_createAgent() {
        return new ICreateAgent() {

            @Override
            public void createTeacherAgent(String id) {
                newTeacherAgent(id);
            }

            @Override
            public void createStudentAgent(String id) {
                newStudentAgent(id);
            }

        };
    }

    @Override
    protected EcoKnowledge make_ecoKnowledge() {
        return new EcoKnowledgeImpl();
    }

    @Override
    protected EcoTeacher make_ecoTeacher() {
        return new EcoTeacherImpl();
    }

    @Override
    protected EcoStudent make_ecoStudent() {
        return new EcoStudentImpl();
    }

}
