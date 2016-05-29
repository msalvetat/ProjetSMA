package m2dl.jlm.projetsma.services.impl.message;

public class InformStudentMessage extends AbstractMessage {

    private String teacherId;
    private String roomId;

    public InformStudentMessage(String teacherId, String roomId) {
        super();
        this.teacherId = teacherId;
        this.roomId = roomId;
        this.messageType = EMessageType.INFORM_STUDENT_MESSAGE;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacher(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
