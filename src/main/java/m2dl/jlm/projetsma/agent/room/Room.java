package m2dl.jlm.projetsma.agent.room;

import java.util.List;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.Const;
import m2dl.jlm.projetsma.agent.room.knowledge.IKnowledgeRoom;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.message.AbstractMessage;
import m2dl.jlm.projetsma.services.message.EMessageType;
import m2dl.jlm.projetsma.services.message.RoomPropositionMessage;
import m2dl.jlm.projetsma.services.message.RoomSearchMessage;

public class Room implements ITwoStepsAgent {

    private IKnowledgeRoom           knowledge;
    private String                   id;
    private IMsgBox<AbstractMessage> msgBox;

    private List<AbstractMessage> messages;

    public Room(String id, IKnowledgeRoom knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.msgBox = messagingService.getMsgBox(this.id, AbstractMessage.class);
        this.msgBox.subscribeToGroup(Const.ROOM_GROUP);
    }

    @Override
    public void perceive() {

        messages = msgBox.getMsgs();
    }

    @Override
    public void decideAndAct() {

        if (!messages.isEmpty()) {
            for (AbstractMessage m : messages) {

                if (m.getMessageType() == EMessageType.ROOM_SEARCH) {
                    this.msgBox.send(new RoomPropositionMessage(id, this), ((RoomSearchMessage) m).getTeacherId());
                }
            }
        }
    }

    public IKnowledgeRoom getKnowledge() {
        return knowledge;
    }
}
