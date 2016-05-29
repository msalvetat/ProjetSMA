package m2dl.jlm.projetsma.agent.impl;

import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.agent.impl.room.EcoRoomImpl;
import m2dl.jlm.projetsma.agent.impl.room.knowledge.impl.EcoKnowledgeRoomImpl;
import m2dl.jlm.projetsma.agent.impl.student.EcoStudentImpl;
import m2dl.jlm.projetsma.agent.impl.student.knowledge.impl.EcoKnowledgeStudentImpl;
import m2dl.jlm.projetsma.agent.impl.teacher.EcoTeacherImpl;
import m2dl.jlm.projetsma.agent.impl.teacher.knowledge.impl.EcoKnowledgeTeacherImpl;
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
