package m2dl.jlm.projetsma.agent.knowledge;

import java.util.HashMap;
import java.util.Set;

import m2dl.jlm.projetsma.agent.TeacherAgent;
import m2dl.jlm.projetsma.environment.Room;

public class Knowledge {

	public String id;
	public HashMap<Room, TeacherAgent> allocationsTeacherRoom;

	public HashMap<Room, TeacherAgent> getAllocationsTeacherRoom() {
		return allocationsTeacherRoom;
	}
	public void setAllocationsTeacherRoom(HashMap<Room, TeacherAgent> allocationsTeacherRoom) {
		this.allocationsTeacherRoom = allocationsTeacherRoom;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
	public String getId() {
		return id;
	}
	public Set<Room> rooms;

}
