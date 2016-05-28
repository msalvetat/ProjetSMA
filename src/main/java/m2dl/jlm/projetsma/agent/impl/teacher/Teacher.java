package m2dl.jlm.projetsma.agent.impl.teacher;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import m2dl.jlm.projetsma.agent.IKnowledge;
import m2dl.jlm.projetsma.agent.impl.student.Student;
import m2dl.jlm.projetsma.environment.impl.Room;
import m2dl.jlm.projetsma.services.IMessagingService;

public class Teacher implements ITwoStepsAgent {

    private IKnowledge        knowledge;
    private String            id;
    private IMessagingService messagingService;
    private IMsgBox           msgBox;

    public Teacher(String id, IKnowledge knowledge, IMessagingService messagingService) {
        this.id = id;
        this.knowledge = knowledge;
        this.messagingService = messagingService;
        this.msgBox = messagingService.getMsgBox(this.id, String.class);
    }

    public void perceive() {

        HashSet<Room> rooms = (HashSet<Room>) this.knowledge.getRooms();
        HashMap<Room, Teacher> allocationsTeacherRoom = (HashMap<Room, Teacher>) this.knowledge
            .getAllocationsTeacherRoom();
    }

    public IKnowledge getKnowledge() {
        return knowledge;
    }

    public void decideAndAct() {
        boolean hasDecided = false;
        Set<Room> rooms = this.knowledge.getRooms();
        Iterator<Room> iterator = rooms.iterator();
        Room room;
        HashMap<Room, Teacher> allocationsTeacherRoom = (HashMap<Room, Teacher>) this.knowledge
            .getAllocationsTeacherRoom();
        while (iterator.hasNext() && !hasDecided) {
            room = iterator.next();

            // Decide : we decide the room we can take
            if (roomIsFree(room, allocationsTeacherRoom) &&
                teacherHasNotRoomAlreadyBooked(allocationsTeacherRoom)) {

                // Act : booking the room
                allocationsTeacherRoom.put(room, this);
                hasDecided = true;
                for (Student etudiant : this.knowledge.getEtudiants()) {
                    this.msgBox.send("Class in room " + room.getName(), etudiant.getId());
                }
            }
        }
    }

    private boolean roomIsFree(Room room, HashMap<Room, Teacher> allocationsTeacherRoom) {
        return !allocationsTeacherRoom.containsKey(room);
    }

    private boolean teacherHasNotRoomAlreadyBooked(HashMap<Room, Teacher> allocationsTeacherRoom) {
        return !allocationsTeacherRoom.containsValue(this);
    }

    public String getId() {
        return id;
    }

}
