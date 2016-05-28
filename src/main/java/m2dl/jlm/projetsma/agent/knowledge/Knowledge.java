package m2dl.jlm.projetsma.agent.knowledge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.IKnowledge;
import m2dl.jlm.projetsma.agent.impl.student.Student;
import m2dl.jlm.projetsma.agent.impl.teacher.Teacher;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.environment.impl.Room;

public class Knowledge implements IKnowledge {

    public String                 id;
    public HashMap<Room, Teacher> allocationsTeacherRoom;
    private IEnvironment          environment;
    public Set<Room>              rooms;
    public Set<Student>           etudiants;

    public Knowledge(IEnvironment environment) {
        this.environment = environment;
    }

    public Map<Room, Teacher> getAllocationsTeacherRoom() {
        return environment.getAllocationsTeacherRoom();
    }

    public void setAllocationsTeacherRoom(HashMap<Room, Teacher> allocationsTeacherRoom) {
        environment.setAllocationsTeacherRoom(allocationsTeacherRoom);
    }

    public Set<Room> getRooms() {
        return environment.getRooms();
    }

    public void setRooms(Set<Room> rooms) {
        environment.setRooms(rooms);
    }

    public String getId() {
        return id;
    }

    public Set<Student> getEtudiants() {
        return environment.getEtudiants();
    }

    public void setEtudiants(Set<Student> etudiants) {
        environment.setEtudiants(etudiants);
    }

}
