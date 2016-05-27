package m2dl.jlm.projetsma.agent.impl;

import java.util.Iterator;

import fr.irit.smac.libs.tooling.messaging.AgentMessaging;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.services.IMessagingService;

public class StudentAgent implements ITwoStepsAgent {

    private Knowledge         knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IEnvironment      environment;
    private IMsgBox           msgBox;

    public StudentAgent(String id, IEnvironment environment, IMessagingService messagingService) {
        this.id = id;
        this.environment = environment;
        this.knowledge = new Knowledge(environment);
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
        environment.getEtudiants().add(this);
    }

    public void perceive() {
        Iterator messagesIt = this.msgBox.getMsgs().iterator();
        // Read messages
        while (messagesIt.hasNext()) {
            String msg = (String) messagesIt.next();
            System.out.println(msg);
        }
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {

    }

    public String getId() {
        return id;
    }

}
