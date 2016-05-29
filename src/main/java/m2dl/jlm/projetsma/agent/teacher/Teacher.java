package m2dl.jlm.projetsma.agent.teacher;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.Const;
import m2dl.jlm.projetsma.agent.teacher.knowledge.IKnowledgeTeacher;
import m2dl.jlm.projetsma.environment.Course;
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

    public Teacher(String id, IKnowledgeTeacher knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.msgBox = messagingService.getMsgBox(this.id, AbstractMessage.class);
        msgBox.subscribeToGroup(Const.TEACHER_GROUP);
    }

    public void perceive() {

        for (AbstractMessage m : this.msgBox.getMsgs()) {
            if (m.getMessageType() == EMessageType.ROOM_PROPOSITION) {
                this.knowledge.getRoomPropositionMessage().add((RoomPropositionMessage) m);
            }
        }
    }

    public IKnowledgeTeacher getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {

        for (RoomPropositionMessage m : this.knowledge.getRoomPropositionMessage()) {

            if (m.getRoom().getKnowledge().isFree()) {
                String roomId = m.getRoomId();
                m.getRoom().getKnowledge().setFree(false);
                this.knowledge.setLookingForRoom(false);
                System.out.println(id + " " + roomId);
                this.msgBox.sendToGroup(new InformStudentMessage(new Course(id, roomId)), Const.STUDENT_GROUP);
                break;
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
