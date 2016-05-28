package m2dl.jlm.projetsma.agent.knowledge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.student.Student;
import m2dl.jlm.projetsma.agent.impl.teacher.Teacher;
import m2dl.jlm.projetsma.environment.impl.Room;

public interface IKnowledge {

    public Map<Room, Teacher> getAllocationsTeacherRoom();

    public void setAllocationsTeacherRoom(HashMap<Room, Teacher> allocationsTeacherRoom);

    public Set<Room> getRooms();

    public void setRooms(Set<Room> rooms);

    public String getId();

    public Set<Student> getEtudiants();

    public void setEtudiants(Set<Student> etudiants);
}
