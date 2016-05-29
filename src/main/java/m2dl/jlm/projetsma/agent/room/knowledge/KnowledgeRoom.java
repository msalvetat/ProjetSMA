package m2dl.jlm.projetsma.agent.room.knowledge;

import java.util.ArrayList;
import java.util.List;

import m2dl.jlm.projetsma.services.message.RoomSearchMessage;

public class KnowledgeRoom implements IKnowledgeRoom {

    private boolean                 free;
    private List<RoomSearchMessage> roomSearchMessages = new ArrayList<>();

    public KnowledgeRoom() {
        this.free = true;
    }

    @Override
    public boolean isFree() {
        return this.free;
    }

    @Override
    public void setFree(boolean free) {
        this.free = free;
    }

    @Override
    public List<RoomSearchMessage> getRoomSearchMessages() {
        return roomSearchMessages;
    }
}
