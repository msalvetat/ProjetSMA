package m2dl.jlm.projetsma.agent.teacher.knowledge;

import java.util.List;

import m2dl.jlm.projetsma.services.message.RoomPropositionMessage;

public interface IKnowledgeTeacher {

    public List<RoomPropositionMessage> getRoomPropositionMessage();
    
    public boolean isLookingForRooms();
    
    public void setLookingForRoom(boolean lookingForRoom);
}
