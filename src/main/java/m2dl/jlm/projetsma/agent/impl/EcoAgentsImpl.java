package m2dl.jlm.projetsma.agent.impl;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.ICreateAgent;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.services.IMessagingService;
import sma.EcoAgents;

public class EcoAgentsImpl extends EcoAgents {

    private IEnvironment environment;
    private IMessagingService messagingService;

    @Override
    public void start() {
        environment = requires().environmentService();
        messagingService = requires().agentMessaging();
    }

    @Override
    protected ICreateAgent make_createAgent() {
        return new ICreateAgent() {

            @Override
            public Teacher createTeacherAgent(String id) {
                // TODO : instantiate with Newcomponent() ?
                return make_Teacher(id);
            }

            @Override
            public Student createStudentAgent(String id) {
                return make_Student(id);
            }

        };
    }
    
    @Override
    protected Teacher make_Teacher(final String id) {
        return new Teacher() {

            @Override
            public ITwoStepsAgent make_agent() {
                return new TeacherAgent(id, environment, messagingService);
            }

        };
    }

    @Override
    protected Student make_Student(final String id) {
        return new Student() {

            @Override
            public ITwoStepsAgent make_agent() {
                return new StudentAgent(id, environment, messagingService);
            }

        };
    }

}
