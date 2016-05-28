package m2dl.jlm.projetsma.environment.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.StudentAgent;
import m2dl.jlm.projetsma.agent.impl.TeacherAgent;
import m2dl.jlm.projetsma.environment.IEnvironment;

public class EnvironmentImpl extends sma.Environment{

    @Override
    protected void start() {
        provides().environment().init();
    }
    
    @Override
    protected IEnvironment make_environment() {
        
        return new IEnvironment() {
            
            Set<Room> rooms;
            Set<StudentAgent> etudiants;
            Map<Room, TeacherAgent> allocationsTeacherRoom;

            public void init() {
                
                this.rooms = new HashSet<Room>();
                this.rooms.add(new Room("A1"));
                this.rooms.add(new Room("A2"));
                this.rooms.add(new Room("A3"));
                this.etudiants = new HashSet<StudentAgent>();
                this.allocationsTeacherRoom = new HashMap<Room,TeacherAgent>();
            };
            
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

            public Set<StudentAgent> getEtudiants() {
                return etudiants;
            }

            public void setEtudiants(Set<StudentAgent> etudiants) {
                this.etudiants = etudiants;
            }

        };
    }

}
