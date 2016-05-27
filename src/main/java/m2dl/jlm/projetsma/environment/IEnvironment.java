package m2dl.jlm.projetsma.environment;

import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.StudentAgent;
import m2dl.jlm.projetsma.agent.impl.TeacherAgent;
import m2dl.jlm.projetsma.environment.impl.Room;

public interface IEnvironment {

    public void init();
    
    public Set<Room> getRooms();
    
    public void setRooms(Set<Room> rooms);
    
    public Map<Room, TeacherAgent> getAllocationsTeacherRoom();
    
    public void setAllocationsTeacherRoom(Map<Room, TeacherAgent> allocationsTeacherRoom);
    
    public Set<StudentAgent> getEtudiants();
    
    public void setEtudiants(Set<StudentAgent> etudiants);

}
