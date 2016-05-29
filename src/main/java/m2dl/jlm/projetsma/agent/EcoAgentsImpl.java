package m2dl.jlm.projetsma.agent;

import m2dl.jlm.projetsma.agent.room.EcoRoomImpl;
import m2dl.jlm.projetsma.agent.room.knowledge.EcoKnowledgeRoomImpl;
import m2dl.jlm.projetsma.agent.student.EcoStudentImpl;
import m2dl.jlm.projetsma.agent.student.knowledge.EcoKnowledgeStudentImpl;
import m2dl.jlm.projetsma.agent.teacher.EcoTeacherImpl;
import m2dl.jlm.projetsma.agent.teacher.knowledge.EcoKnowledgeTeacherImpl;
import sma.agent.EcoRoom;
import sma.agent.EcoStudent;
import sma.agent.EcoTeacher;
import sma.ecoAgents.EcoAgents;
import sma.ecoKnowledgeRoom.EcoKnowledgeRoom;
import sma.ecoKnowledgeStudent.EcoKnowledgeStudent;
import sma.ecoKnowledgeTeacher.EcoKnowledgeTeacher;

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

            @Override
            public void createRoomAgent(String id) {
                newRoomAgent(id);
            }

        };
    }

    @Override
    protected EcoTeacher make_ecoTeacher() {
        return new EcoTeacherImpl();
    }

    @Override
    protected EcoStudent make_ecoStudent() {
        return new EcoStudentImpl();
    }

    @Override
    protected EcoRoom make_ecoRoom() {
        return new EcoRoomImpl();
    }

    @Override
    protected EcoKnowledgeRoom make_ecoKnowledgeRoom() {
        return new EcoKnowledgeRoomImpl();
    }

    @Override
    protected EcoKnowledgeTeacher make_ecoKnowledgeTeacher() {
        return new EcoKnowledgeTeacherImpl();
    }

    @Override
    protected EcoKnowledgeStudent make_ecoKnowledgeStudent() {
        return new EcoKnowledgeStudentImpl();
    }

}
