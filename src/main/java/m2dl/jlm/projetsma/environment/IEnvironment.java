package m2dl.jlm.projetsma.environment;

import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.student.Student;
import m2dl.jlm.projetsma.agent.impl.teacher.Teacher;
import m2dl.jlm.projetsma.environment.impl.Room;

public interface IEnvironment {

    public void init();
    
    public Set<Room> getRooms();
    
    public void setRooms(Set<Room> rooms);
    
    public Map<Room, Teacher> getAllocationsTeacherRoom();
    
    public void setAllocationsTeacherRoom(Map<Room, Teacher> allocationsTeacherRoom);
    
    public Set<Student> getEtudiants();
    
    public void setEtudiants(Set<Student> etudiants);

}
