package m2dl.jlm.projetsma.agent.knowledge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.StudentAgent;
import m2dl.jlm.projetsma.agent.impl.TeacherAgent;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.environment.impl.Room;

public class Knowledge {

	public String id;
	public HashMap<Room, TeacherAgent> allocationsTeacherRoom;
    private IEnvironment environment;
    public Set<Room> rooms;
    public Set<StudentAgent> etudiants;
    
	public Knowledge (IEnvironment environment) {
	    this.environment = environment;
	}
	
	public Map<Room, TeacherAgent> getAllocationsTeacherRoom() {
		return environment.getAllocationsTeacherRoom();
	}
	public void setAllocationsTeacherRoom(HashMap<Room, TeacherAgent> allocationsTeacherRoom) {
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

	public Set<StudentAgent> getEtudiants() {
		return environment.getEtudiants();
	}
	public void setEtudiants(Set<StudentAgent> etudiants) {
	    environment.setEtudiants(etudiants);
	}

}
