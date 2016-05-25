package m2dl.jlm.projetsma.agent.impl;

import m2dl.jlm.projetsma.agent.ICreateAgent;
import sma.EcoAgents;

public class EcoAgentsImpl extends EcoAgents{
    
	public EcoAgentsImpl() {
	}
	
    @Override
    protected Teacher make_Teacher(String id) {
        return new TeacherImpl(id);
    }


	@Override
	protected ICreateAgent make_create() {
		return new ICreateAgent() {
            @Override
            public Teacher.Component createTeacherAgent(String name) {
                return newTeacher(name);
            }
        };
	}
}
