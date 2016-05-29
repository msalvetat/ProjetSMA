package m2dl.jlm.projetsma.agent.room.knowledge;

import sma.ecoKnowledgeRoom.EcoKnowledgeRoom;

public class EcoKnowledgeRoomImpl extends EcoKnowledgeRoom {

    @Override
    protected KnowledgeRoomS make_KnowledgeRoomS(String id) {
        return new KnowledgeRoomS() {

            @Override
            protected IKnowledgeRoom make_knowledge() {
                return new KnowledgeRoom();
            }
        };
    }
}
