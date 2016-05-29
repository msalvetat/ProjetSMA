package m2dl.jlm.projetsma.agent.impl.room;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.IKnowledge;
import m2dl.jlm.projetsma.services.IMessagingService;

public class Room implements ITwoStepsAgent{

    private IKnowledge        knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IMsgBox           msgBox;
    
    public Room(String id, IKnowledge knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
    }
    
    @Override
    public void decideAndAct() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void perceive() {
        // TODO Auto-generated method stub
        
    }

}
