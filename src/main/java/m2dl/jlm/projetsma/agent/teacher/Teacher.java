package m2dl.jlm.projetsma.agent.teacher;

import java.util.List;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.Const;
import m2dl.jlm.projetsma.agent.teacher.knowledge.IKnowledgeTeacher;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.message.AbstractMessage;
import m2dl.jlm.projetsma.services.message.EMessageType;
import m2dl.jlm.projetsma.services.message.InformStudentMessage;
import m2dl.jlm.projetsma.services.message.RoomPropositionMessage;
import m2dl.jlm.projetsma.services.message.RoomSearchMessage;

public class Teacher implements ITwoStepsAgent {

    private IKnowledgeTeacher        knowledge;
    private String                   id;
    private IMsgBox<AbstractMessage> msgBox;
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

                if (m.getMessageType() == EMessageType.ROOM_PROPOSITION) {

                    RoomPropositionMessage message = (RoomPropositionMessage) m;
                    if (message.getRoom().getKnowledge().isFree()) {
                        String roomId = message.getRoomId();
                        message.getRoom().getKnowledge().setFree(false);
                        this.knowledge.setLookingForRoom(false);
                        System.out.println(id + " " + roomId);
                        this.msgBox.sendToGroup(new InformStudentMessage(id, roomId), Const.STUDENT_GROUP);
                        break;
                    }
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
