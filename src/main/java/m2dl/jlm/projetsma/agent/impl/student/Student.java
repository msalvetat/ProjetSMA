package m2dl.jlm.projetsma.agent.impl.student;

import java.util.Iterator;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.IKnowledge;
import m2dl.jlm.projetsma.services.IMessagingService;

public class Student implements ITwoStepsAgent {

    private IKnowledge        knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IMsgBox           msgBox;

    public Student(String id, IKnowledge knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
        knowledge.getEtudiants().add(this);
    }

    public void perceive() {
        Iterator messagesIt = this.msgBox.getMsgs().iterator();
        // Read messages
        while (messagesIt.hasNext()) {
            String msg = (String) messagesIt.next();
            System.out.println(msg);
        }
    }

    public IKnowledge getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {

    }

    public String getId() {
        return id;
    }

}
