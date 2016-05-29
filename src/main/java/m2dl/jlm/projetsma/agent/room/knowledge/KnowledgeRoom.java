package m2dl.jlm.projetsma.agent.room.knowledge;

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
