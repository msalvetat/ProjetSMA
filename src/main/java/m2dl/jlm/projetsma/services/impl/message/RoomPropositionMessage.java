package m2dl.jlm.projetsma.services.impl.message;

import m2dl.jlm.projetsma.agent.impl.room.Room;

public class RoomPropositionMessage extends AbstractMessage {

    private String message;
    private Room room;
    
    public RoomPropositionMessage(String message, Room room) {
        super();
        this.message = message;
        this.room = room;
        this.messageType = EMessageType.ROOM_PROPOSITION;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
