package m2dl.jlm.projetsma.agent.impl.teacher;

import java.util.List;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.impl.teacher.knowledge.IKnowledgeTeacher;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.impl.Message;

public class Teacher implements ITwoStepsAgent {

    private IKnowledgeTeacher        knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IMsgBox<Message>  msgBox;
    private String            room;
    private List<Message>     messages;

    public Teacher(String id, IKnowledgeTeacher knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
    }

    public void perceive() {

        messages = msgBox.getMsgs();
    }

    public IKnowledgeTeacher getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {

        if (!messages.isEmpty()) {
            for (Message m : messages) {

                if (m.getRoom().getKnowledge().isFree()) {
                    room = m.getMessage();
                    m.getRoom().getKnowledge().setFree(false);
                    this.knowledge.setLookingForRoom(false);
                    System.out.println(id + " " + room);
                    break;
                }
            }

        }

        if (this.knowledge.isLookingForRooms()) {
            msgBox.sendToGroup(new Message(id), "room");
        }
    }

    public String getId() {
        return id;
    }

}
