package m2dl.jlm.projetsma.agent.impl.teacher.knowledge.impl;

import m2dl.jlm.projetsma.agent.impl.teacher.knowledge.IKnowledgeTeacher;

public class KnowledgeTeacher implements IKnowledgeTeacher {

    private boolean lookingForRoom;

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
