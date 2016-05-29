package m2dl.jlm.projetsma;

import m2dl.jlm.projetsma.agent.EcoAgentsImpl;
import m2dl.jlm.projetsma.environment.EnvironmentImpl;
import m2dl.jlm.projetsma.services.ServicesImpl;
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
