package m2dl.jlm.projetsma.agent.teacher.knowledge;

import java.util.ArrayList;
import java.util.List;

import m2dl.jlm.projetsma.services.message.RoomPropositionMessage;

public class KnowledgeTeacher implements IKnowledgeTeacher {

    private boolean lookingForRoom;
    private List<RoomPropositionMessage> roomPropositionMessage = new ArrayList<>();

    public List<RoomPropositionMessage> getRoomPropositionMessage() {
        return roomPropositionMessage;
    }

    public KnowledgeTeacher() {
        this.lookingForRoom = true;
    }

    @Override
    public boolean isLookingForRooms() {
        return this.lookingForRoom;
    }

    @Override
    public void setLookingForRoom(boolean lookingForRoom) {
        this.lookingForRoom = lookingForRoom;
    }

}
