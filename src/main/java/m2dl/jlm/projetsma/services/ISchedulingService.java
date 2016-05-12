package m2dl.jlm.projetsma.services;

import m2dl.jlm.projetsma.agent.impl.TeacherAgent;

public interface ISchedulingService {

    public void createService();
    
    public void doStep();
    
    public void addAgent(TeacherAgent agent);
}
