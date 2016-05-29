package m2dl.jlm.projetsma.environment;

public class Course {

    private String teacherId;
    private String roomId;

    public Course(String teacherId, String roomId) {
        this.teacherId = teacherId;
        this.roomId = roomId;
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
