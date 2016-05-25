package m2dl.jlm.projetsma.agent.impl;

import java.util.HashMap;
import java.util.HashSet;

import fr.irit.smac.libs.tooling.messaging.AgentMessaging;
import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge;
import m2dl.jlm.projetsma.environment.impl.EnvironmentImpl;
import m2dl.jlm.projetsma.environment.impl.Room;
import sma.Environment;
import sma.SMA;
import sma.Environment.Component;

public class TeacherAgent implements ITwoStepsAgent {

	Knowledge knowledge;
	String id;
	IMsgBox msgBox;
	SMA.Component sma;

	public TeacherAgent(String id) {
		this.id = id;
		this.knowledge = new Knowledge();
		this.msgBox = AgentMessaging.getMsgBox(this.id, String.class);
//	    this.environment = ecoAgent.environment;		
	}

	public void perceive() {

//	    Environment.Component e = environment.newComponent();
//		HashSet<Room> rooms = (HashSet<Room>) environment.getRooms();
//		HashMap<Room, TeacherAgent> allocationsTeacherRoom = (HashMap<Room, TeacherAgent>) EnvironmentImpl.getInstance()
//				.getAllocationsTeacherRoom();
//		knowledge.setRooms(rooms);
//		knowledge.setAllocationsTeacherRoom(allocationsTeacherRoom);
//		knowledge.setEtudiants((HashSet<EtudiantAgent>)environment.getInstance().getEtudiants());
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void decideAndAct() {
//		boolean hasDecided = false;
//		Set<Room> rooms = this.knowledge.getRooms();
//		Iterator<Room> iterator = rooms.iterator();
//		Room room;
//		HashMap<Room, TeacherAgent> allocationsTeacherRoom = this.knowledge.getAllocationsTeacherRoom();
//		while(iterator.hasNext() && !hasDecided) {
//			room = iterator.next();
//			
//			// Decide : we decide the room we can take
//			if (roomIsFree(room, allocationsTeacherRoom) && teacherHasNotRoomAlreadyBooked(allocationsTeacherRoom)) {
//				
//				// Act : booking the room
//				allocationsTeacherRoom.put(room, this);
//				hasDecided = true;
//				for(EtudiantAgent etudiant : this.knowledge.getEtudiants()) {
//					this.msgBox.send("Class in room " + room.getName(),etudiant.getId());
//				}
//			}
//		}
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
