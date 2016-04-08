package m2dl.jlm.projetsma.environment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.TeacherAgent;

public class Environment {

	Set<Room> rooms;
	Map<Room, TeacherAgent> allocationsTeacherRoom;

	private Environment() {
		this.rooms = new HashSet<Room>();
		this.rooms.add(new Room("A1"));
		this.rooms.add(new Room("A2"));
		this.rooms.add(new Room("A3"));
		this.allocationsTeacherRoom = new HashMap<Room,TeacherAgent>();
	};

	private static Environment INSTANCE;

	public static Environment getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new Environment();
		}
		return INSTANCE;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Map<Room, TeacherAgent> getAllocationsTeacherRoom() {
		return allocationsTeacherRoom;
	}

	public void setAllocationsTeacherRoom(Map<Room, TeacherAgent> allocationsTeacherRoom) {
		this.allocationsTeacherRoom = allocationsTeacherRoom;
	}

}
