package m2dl.jlm.projetsma.environment.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import m2dl.jlm.projetsma.agent.impl.student.Student;
import m2dl.jlm.projetsma.agent.impl.teacher.Teacher;
import m2dl.jlm.projetsma.environment.IEnvironment;
import sma.environment.Environment;

public class EnvironmentImpl extends Environment {

    @Override
    protected void start() {
        provides().environment().init();
    }

    @Override
    protected IEnvironment make_environment() {

        return new IEnvironment() {

            Set<Room> rooms;
            Set<Student> etudiants;
            Map<Room, Teacher> allocationsTeacherRoom;

            public void init() {

                this.rooms = new HashSet<Room>();
                this.rooms.add(new Room("A1"));
                this.rooms.add(new Room("A2"));
                this.rooms.add(new Room("A3"));
                this.etudiants = new HashSet<Student>();
                this.allocationsTeacherRoom = new HashMap<Room, Teacher>();
            };

            public Set<Room> getRooms() {
                return rooms;
            }

            public void setRooms(Set<Room> rooms) {
                this.rooms = rooms;
            }

            public Map<Room, Teacher> getAllocationsTeacherRoom() {
                return allocationsTeacherRoom;
            }

            public void setAllocationsTeacherRoom(Map<Room, Teacher> allocationsTeacherRoom) {
                this.allocationsTeacherRoom = allocationsTeacherRoom;
            }

            public Set<Student> getEtudiants() {
                return etudiants;
            }

            public void setEtudiants(Set<Student> etudiants) {
                this.etudiants = etudiants;
            }

        };
    }

}
