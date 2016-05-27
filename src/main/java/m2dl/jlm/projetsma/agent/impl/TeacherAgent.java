package m2dl.jlm.projetsma.agent.impl;

import java.util.HashMap;
import java.util.HashSet;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge;
import m2dl.jlm.projetsma.environment.IEnvironment;
import m2dl.jlm.projetsma.environment.impl.Room;
import m2dl.jlm.projetsma.services.IMessagingService;

public class TeacherAgent implements ITwoStepsAgent {

    private Knowledge         knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IEnvironment      environment;
    private IMsgBox           msgBox;

    public TeacherAgent(String id, IEnvironment environment, IMessagingService messagingService) {
        this.id = id;
        this.environment = environment;
        this.knowledge = new Knowledge();
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
    }

    public void perceive() {

        HashSet<Room> rooms = (HashSet<Room>) environment.getRooms();
        HashMap<Room, TeacherAgent> allocationsTeacherRoom = (HashMap<Room, TeacherAgent>) environment
            .getAllocationsTeacherRoom();
        knowledge.setRooms(rooms);
        knowledge.setAllocationsTeacherRoom(allocationsTeacherRoom);
        knowledge.setEtudiants((HashSet<EtudiantAgent>) environment.getEtudiants());
    }

    public Knowledge getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {
        // boolean hasDecided = false;
        // Set<Room> rooms = this.knowledge.getRooms();
        // Iterator<Room> iterator = rooms.iterator();
        // Room room;
        // HashMap<Room, TeacherAgent> allocationsTeacherRoom =
        // this.knowledge.getAllocationsTeacherRoom();
        // while(iterator.hasNext() && !hasDecided) {
        // room = iterator.next();
        //
        // // Decide : we decide the room we can take
        // if (roomIsFree(room, allocationsTeacherRoom) &&
        // teacherHasNotRoomAlreadyBooked(allocationsTeacherRoom)) {
        //
        // // Act : booking the room
        // allocationsTeacherRoom.put(room, this);
        // hasDecided = true;
        // for(EtudiantAgent etudiant : this.knowledge.getEtudiants()) {
        // this.msgBox.send("Class in room " + room.getName(),etudiant.getId());
        // }
        // }
        // }
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
