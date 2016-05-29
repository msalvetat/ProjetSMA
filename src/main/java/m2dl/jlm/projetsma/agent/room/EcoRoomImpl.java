package m2dl.jlm.projetsma.agent.room;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import sma.agent.EcoRoom;

public class EcoRoomImpl extends EcoRoom {

    @Override
    protected RoomS make_RoomS(final String id) {

        return new RoomS() {

            @Override
            protected ITwoStepsAgent make_room() {
                ITwoStepsAgent room = new Room(id, requires().knowledge(), eco_requires().messagingService());
                eco_requires().strategyService().addAgent(room);
                return room;
            }
        };
    }
}
