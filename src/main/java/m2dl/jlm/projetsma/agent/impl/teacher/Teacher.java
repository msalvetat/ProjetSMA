package m2dl.jlm.projetsma.agent.impl.teacher;

import java.util.List;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.Const;
import m2dl.jlm.projetsma.agent.impl.teacher.knowledge.IKnowledgeTeacher;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.impl.message.AbstractMessage;
import m2dl.jlm.projetsma.services.impl.message.InformStudentMessage;
import m2dl.jlm.projetsma.services.impl.message.RoomPropositionMessage;
import m2dl.jlm.projetsma.services.impl.message.RoomSearchMessage;

public class Teacher implements ITwoStepsAgent {

    private IKnowledgeTeacher        knowledge;
    private String                   id;
    private IMsgBox<AbstractMessage> msgBox;
    private String                   room;
    private List<AbstractMessage>    messages;

    public Teacher(String id, IKnowledgeTeacher knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.msgBox = messagingService.getMsgBox(this.id, AbstractMessage.class);
        msgBox.subscribeToGroup(Const.TEACHER_GROUP);
    }

    public void perceive() {

        messages = msgBox.getMsgs();
    }

    public IKnowledgeTeacher getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {

        if (!messages.isEmpty()) {
            for (AbstractMessage m : messages) {

                switch (m.getMessageType()) {

                    case ROOM_PROPOSITION:
                        
                        RoomPropositionMessage message = (RoomPropositionMessage) m;
                        if (message.getRoom().getKnowledge().isFree()) {
                            room = message.getMessage();
                            message.getRoom().getKnowledge().setFree(false);
                            this.knowledge.setLookingForRoom(false);
                            this.msgBox.sendToGroup(new InformStudentMessage(id, room), Const.STUDENT_GROUP);
                            System.out.println(id + " " + room);
                            break;
                        }
                        break;

                    default:
                        break;
                }

            }

        }

        if (this.knowledge.isLookingForRooms()) {
            msgBox.sendToGroup(new RoomSearchMessage(id), Const.ROOM_GROUP);
        }
    }

    public String getId() {
        return id;
    }

}
