package m2dl.jlm.projetsma.services;

import m2dl.jlm.projetsma.agent.impl.TeacherAgent;

public interface ISchedulingService {

    public void init();
    
    public void doStep();
    
    public void addAgent(TeacherAgent agent);
    
    public void shutdown();
}
