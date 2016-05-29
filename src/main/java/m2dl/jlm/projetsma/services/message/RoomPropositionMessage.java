package m2dl.jlm.projetsma.services.message;

import m2dl.jlm.projetsma.agent.room.Room;

public class RoomPropositionMessage extends AbstractMessage {

    private String roomId;
    private Room room;
    
    public RoomPropositionMessage(String roomId, Room room) {
        super();
        this.roomId = roomId;
        this.room = room;
        this.messageType = EMessageType.ROOM_PROPOSITION;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

}
