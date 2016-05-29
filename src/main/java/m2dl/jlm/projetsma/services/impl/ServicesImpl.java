
package m2dl.jlm.projetsma.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.Executors;

import fr.irit.smac.libs.tooling.messaging.AgentMessaging;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.TwoStepsSystemStrategy;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.ISchedulingService;
import sma.services.Services;

public class ServicesImpl extends Services {

    @Override
    protected void start() {
        provides().systemStrategy().init();
    }

    @Override
    protected ISchedulingService make_systemStrategy() {
        return new ISchedulingService() {

            private TwoStepsSystemStrategy twoStepsSystemStrategy;

            @Override
            public void doStep() {
                twoStepsSystemStrategy.step();
            }

            @Override
            public void init() {
                this.twoStepsSystemStrategy = new TwoStepsSystemStrategy(new HashSet<ITwoStepsAgent>(),
                    Executors.newFixedThreadPool(8));
            }

            @Override
            public void addAgent(ITwoStepsAgent agent) {
                this.twoStepsSystemStrategy.addAgent(agent);
            }

            @Override
            public void shutdown() {
                this.twoStepsSystemStrategy.shutdown();
            }

            @Override
            public Collection<ITwoStepsAgent> getAgents() {
                return this.twoStepsSystemStrategy.getAgents();
            }
        };
    }

    @Override
    protected IMessagingService make_agentMessaging() {
        return new IMessagingService() {

            @Override
            public IMsgBox getMsgBox(String agentId, Class<?> messageClassType) {
                return AgentMessaging.getMsgBox(agentId, messageClassType);
            }

            @Override
            public boolean sendToGroup(Message msg, String groupId, String agentId) {
                return AgentMessaging.getMsgBox(agentId, Message.class).sendToGroup(msg, groupId);
            }
        };
    }

}
