package m2dl.jlm.projetsma.services.impl;

import m2dl.jlm.projetsma.agent.impl.room.Room;

public class Message {

    private String message;
    private Room room;
    
    public Message(String message) {
        super();
        this.message = message;
    }

    public Message(String message, Room room) {
        super();
        this.message = message;
        this.room = room;
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
