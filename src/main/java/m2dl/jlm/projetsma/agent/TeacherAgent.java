package m2dl.jlm.projetsma.agent;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge;
import m2dl.jlm.projetsma.environment.Environment;
import m2dl.jlm.projetsma.environment.Room;

public class TeacherAgent implements ITwoStepsAgent {

	Knowledge knowledge;
	String id;

	public TeacherAgent(String id) {
		this.id = id;
		this.knowledge = new Knowledge();
	}

	public void perceive() {

		HashSet<Room> rooms = (HashSet<Room>) Environment.getInstance().getRooms();
		HashMap<Room, TeacherAgent> allocationsTeacherRoom = (HashMap<Room, TeacherAgent>) Environment.getInstance()
				.getAllocationsTeacherRoom();
		knowledge.setRooms(rooms);
		knowledge.setAllocationsTeacherRoom(allocationsTeacherRoom);
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void decideAndAct() {
		boolean hasDecided = false;
		Set<Room> rooms = this.knowledge.getRooms();
		Iterator<Room> iterator = rooms.iterator();
		Room room;
		HashMap<Room, TeacherAgent> allocationsTeacherRoom = this.knowledge.getAllocationsTeacherRoom();
		while(iterator.hasNext() && !hasDecided) {
			room = iterator.next();
			
			// Decide : we decide the room we can take
			if (roomIsFree(room, allocationsTeacherRoom) && teacherHasNotRoomAlreadyBooked(allocationsTeacherRoom)) {
				
				// Act : booking the room
				allocationsTeacherRoom.put(room, this);
				hasDecided = true;
			}
		}
	}

	private boolean roomIsFree(Room room, HashMap<Room, TeacherAgent> allocationsTeacherRoom) {
		return !allocationsTeacherRoom.containsKey(room);
	}

	private boolean teacherHasNotRoomAlreadyBooked(HashMap<Room, TeacherAgent> allocationsTeacherRoom) {
		return !allocationsTeacherRoom.containsValue(this);
	}

	public String getId() {
		return id;
	}
	
}
