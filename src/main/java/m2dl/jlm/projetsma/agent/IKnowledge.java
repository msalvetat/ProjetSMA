package m2dl.jlm.projetsma.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.StudentAgent;
import m2dl.jlm.projetsma.agent.impl.TeacherAgent;
import m2dl.jlm.projetsma.environment.impl.Room;

public interface IKnowledge {

    public Map<Room, TeacherAgent> getAllocationsTeacherRoom();

    public void setAllocationsTeacherRoom(HashMap<Room, TeacherAgent> allocationsTeacherRoom);

    public Set<Room> getRooms();

    public void setRooms(Set<Room> rooms);

    public String getId();

    public Set<StudentAgent> getEtudiants();

    public void setEtudiants(Set<StudentAgent> etudiants);
}
