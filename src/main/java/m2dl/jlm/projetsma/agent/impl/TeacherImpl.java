package m2dl.jlm.projetsma.agent.impl;

import m2dl.jlm.projetsma.agent.IGetTeacherAgent;
import sma.EcoAgents.Teacher;

public class TeacherImpl extends Teacher{

    private TeacherAgent teacher;
    
    public TeacherImpl(String id) {
        teacher = new TeacherAgent(id);
    }
    
    @Override
    protected IGetTeacherAgent make_agent() {
        return new IGetTeacherAgent() {
            
            @Override
            public TeacherAgent getTeacherAgent() {
                return teacher;
            }
        };
    }

}
