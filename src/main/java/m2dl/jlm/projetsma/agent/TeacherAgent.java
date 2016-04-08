package m2dl.jlm.projetsma.agent;

import java.util.HashMap;
import java.util.HashSet;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge;
import m2dl.jlm.projetsma.environment.Environment;
import m2dl.jlm.projetsma.environment.Room;

public class TeacherAgent implements ITwoStepsAgent{

	Knowledge knowledge;
	String id;
	
	public TeacherAgent(String id) {
		this.id = id;
		this.knowledge = new Knowledge();
	}
	
	public void perceive() {
		
		HashSet<Room> rooms = (HashSet<Room>) Environment.getInstance().getRooms();
		HashMap<Room,TeacherAgent> allocationsTeacherRoom = (HashMap<Room, TeacherAgent>) Environment.getInstance().getAllocationsTeacherRoom();
		knowledge.setRooms(rooms);
		knowledge.setAllocationsTeacherRoom(allocationsTeacherRoom);
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void decideAndAct() {
		// TODO Auto-generated method stub
		
	}

}
