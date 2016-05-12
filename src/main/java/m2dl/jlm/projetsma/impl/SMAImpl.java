package m2dl.jlm.projetsma.impl;

import m2dl.jlm.projetsma.agent.impl.EcoAgentsImpl;
import m2dl.jlm.projetsma.services.impl.ServicesImpl;
import sma.EcoAgents;
import sma.SMA;
import sma.Services;

public class SMAImpl extends SMA{

    @Override
    protected EcoAgents make_agents() {
        return new EcoAgentsImpl();
    }

    @Override
    protected Services make_services() {
        return new ServicesImpl();
    }

}
