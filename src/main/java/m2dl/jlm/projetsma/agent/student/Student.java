package m2dl.jlm.projetsma.agent.student;

import java.util.List;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.Const;
import m2dl.jlm.projetsma.agent.student.knowledge.IKnowledgeStudent;
import m2dl.jlm.projetsma.services.IMessagingService;
import m2dl.jlm.projetsma.services.message.AbstractMessage;
import m2dl.jlm.projetsma.services.message.EMessageType;
import m2dl.jlm.projetsma.services.message.InformStudentMessage;

public class Student implements ITwoStepsAgent {

    private IKnowledgeStudent        knowledge;
    private String                   id;
    private IMsgBox<AbstractMessage> msgBox;

    public Student(String id, IKnowledgeStudent knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.msgBox = messagingService.getMsgBox(this.id, AbstractMessage.class);
        this.msgBox.subscribeToGroup(Const.STUDENT_GROUP);
    }

    public void perceive() {

        for (AbstractMessage m : this.msgBox.getMsgs()) {
            if (m.getMessageType() == EMessageType.ROOM_PROPOSITION) {
                this.knowledge.getInformStudentMessages().add((InformStudentMessage) m);
            }
        }
    }

    public IKnowledgeStudent getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {

        for (InformStudentMessage m : this.knowledge.getInformStudentMessages()) {

            this.knowledge.getCourses().add(m.getCourse());
            // System.out.println(message.getRoomId() + " " +
            // message.getTeacherId());
        }
    }

    public String getId() {
        return id;
    }

}
