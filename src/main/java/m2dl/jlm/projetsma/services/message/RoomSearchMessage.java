package m2dl.jlm.projetsma.services.message;

public class RoomSearchMessage extends AbstractMessage {

    private String teacherId;

    public RoomSearchMessage(String teacherId) {
        super();
        this.teacherId = teacherId;
        this.messageType = EMessageType.ROOM_SEARCH;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
}
