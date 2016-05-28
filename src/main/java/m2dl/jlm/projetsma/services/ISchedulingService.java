package m2dl.jlm.projetsma.services;

import java.util.Collection;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;

public interface ISchedulingService {

    public void init();
    
    public void doStep();
    
    public void addAgent(ITwoStepsAgent agent);
    
    public void shutdown();
    
    public Collection<ITwoStepsAgent> getAgents();
}
