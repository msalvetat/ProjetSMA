package m2dl.jlm.projetsma.agent.impl;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.environment.IEnvironment;
import sma.EcoAgents;

public class EcoAgentsImpl extends EcoAgents {

    private IEnvironment environment;

    @Override
    public void start() {
        environment = requires().environmentService();
    }

    @Override
    protected Teacher make_Teacher(final String id) {
        return new Teacher() {

            @Override
            public ITwoStepsAgent make_agent() {
                return new TeacherAgent(id, environment);
            }

        };
    }

    @Override
    protected ICreateAgent make_createAgent() {
        return new ICreateAgent() {

            @Override
            public Teacher createTeacherAgent(String id) {
                // TODO : instantiate with Newcomponent() ?
                return make_Teacher(id);
            }

        };
    }
    // @Override
    // protected ICreateAgent make_create() {
    // return new ICreateAgent() {
    //
    // @Override
    // public IGetTeacherAgent createTeacherAgent(String id) {
    // // TODO Auto-generated method stub
    // return new IGetTeacherAgent() {
    //
    // @Override
    // public TeacherAgent getTeacherAgent() {
    // // TODO Auto-generated method stub
    // return newTeacherAgent();
    // }
    // };
    // }
    //
    // };
    // }
}
