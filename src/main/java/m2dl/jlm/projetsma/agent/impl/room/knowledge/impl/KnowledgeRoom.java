package m2dl.jlm.projetsma.agent.impl.room.knowledge.impl;

import m2dl.jlm.projetsma.agent.impl.room.knowledge.IKnowledgeRoom;

public class KnowledgeRoom implements IKnowledgeRoom {

    private boolean free;

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
}
