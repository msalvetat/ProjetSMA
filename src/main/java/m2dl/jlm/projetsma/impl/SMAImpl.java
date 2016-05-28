package m2dl.jlm.projetsma.impl;

import m2dl.jlm.projetsma.agent.impl.EcoAgentsImpl;
import m2dl.jlm.projetsma.environment.impl.EnvironmentImpl;
import m2dl.jlm.projetsma.services.impl.ServicesImpl;
import sma.SMA;
import sma.ecoAgents.EcoAgents;
import sma.environment.Environment;
import sma.services.Services;

public class SMAImpl extends SMA{

    
    @Override
    protected EcoAgents make_agents() {
        return new EcoAgentsImpl();
    }

    @Override
    protected Services make_services() {
        return new ServicesImpl();
    }

    @Override
    protected Environment make_environment() {
        return new EnvironmentImpl();
    }
}
