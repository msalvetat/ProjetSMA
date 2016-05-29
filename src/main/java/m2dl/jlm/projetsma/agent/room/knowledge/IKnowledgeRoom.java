package m2dl.jlm.projetsma.agent.room.knowledge;

import java.util.List;

import m2dl.jlm.projetsma.services.message.RoomSearchMessage;

public interface IKnowledgeRoom {

    public List<RoomSearchMessage> getRoomSearchMessages();

    public boolean isFree();
    
    public void setFree(boolean free);
}
