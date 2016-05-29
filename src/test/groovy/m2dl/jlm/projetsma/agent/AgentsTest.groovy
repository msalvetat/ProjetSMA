package m2dl.jlm.projetsma.agent

import m2dl.jlm.projetsma.SMAImpl
import m2dl.jlm.projetsma.agent.room.Room
import m2dl.jlm.projetsma.agent.teacher.Teacher;
import m2dl.jlm.projetsma.environment.IEnvironment
import sma.SMA
import spock.lang.Specification
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class AgentsTest extends Specification {

    def 'check if the 10 rooms are allocated to 10 teachers'() {

        given:
        SMA.Component sma = new SMAImpl().newComponent()
        IEnvironment environment = sma.environmentService()
        
        for (int i=0; i<5; i++) {
            sma.createAgent().createStudentAgent("student" + i);
        }
        
        for (int i=0; i<10; i++) {
            sma.createAgent().createRoomAgent("room" + i)
            sma.createAgent().createTeacherAgent("teacher" + i)
        }
        
        when:
        sma.strategyService().doStep()
        sma.strategyService().doStep()
        sma.strategyService().doStep()
        sma.strategyService().doStep()
        
        sleep(3000)

        then:
        Set<ITwoStepsAgent> agents = sma.strategyService().getAgents()
        boolean allocatedRooms = true
        for (ITwoStepsAgent agent : agents) {
            
            if (agent instanceof Teacher) {
                allocatedRooms = allocatedRooms && !agent.getKnowledge().isLookingForRooms()
            } else if (agent instanceof Room) {
                allocatedRooms = allocatedRooms && !agent.getKnowledge().isFree()
            }
        }
        
        allocatedRooms == true
    }
}
