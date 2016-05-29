package m2dl.jlm.projetsma.agent.impl.room;

import java.util.ArrayList;
import java.util.List;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.Const;
import m2dl.jlm.projetsma.agent.impl.room.knowledge.IKnowledgeRoom;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.impl.Message;

public class Room implements ITwoStepsAgent {

    private IKnowledgeRoom    knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IMsgBox<Message>  msgBox;

    private List<Message> messages;

    public Room(String id, IKnowledgeRoom knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
        msgBox.subscribeToGroup(Const.ROOM_GROUP);
    }

    @Override
    public void perceive() {

        messages = msgBox.getMsgs();
    }

    @Override
    public void decideAndAct() {

        if (!messages.isEmpty()) {
            for (Message m : messages) {
                this.msgBox.send(new Message(id, this), m.getMessage());
            }
        }
    }
    
    public IKnowledgeRoom getKnowledge() {
        return knowledge;
    }
}
