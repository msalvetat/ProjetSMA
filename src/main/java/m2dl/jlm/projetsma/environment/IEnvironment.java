package m2dl.jlm.projetsma.environment;

import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.EtudiantAgent;
import m2dl.jlm.projetsma.agent.impl.TeacherAgent;
import m2dl.jlm.projetsma.environment.impl.Room;

public interface IEnvironment {

    public void init();
    
    public Set<Room> getRooms();
    
    public void setRooms(Set<Room> rooms);
    
    public Map<Room, TeacherAgent> getAllocationsTeacherRoom();
    
    public void setAllocationsTeacherRoom(Map<Room, TeacherAgent> allocationsTeacherRoom);
    
    public Set<EtudiantAgent> getEtudiants();
    
    public void setEtudiants(Set<EtudiantAgent> etudiants);
}
