package m2dl.jlm.projetsma.agent

import m2dl.jlm.projetsma.environment.IEnvironment
import m2dl.jlm.projetsma.impl.SMAImpl
import sma.SMA
import spock.lang.Specification

class TeacherAgentTest extends Specification {
    
    def 'check the step perceive and decideAndAct for a teacher agent'() {

        given:
        SMA.Component sma = new SMAImpl().newComponent()
        IEnvironment environment = sma.environmentService()
        sma.createAgent().createTeacherAgent("sheogorath")

        when:
        sma.strategyService().doStep()
        sleep(1000)

        then:
        environment.getRooms().size() == 3
        // Step decideAndAct make that 1 room has been booked by sheogorath
        environment.getAllocationsTeacherRoom().size() == 1
        sma.strategyService().shutdown()
    }

    def 'check behaviours for 3 agents'() {

        given: "3 rooms and 3 teachers"
        SMA.Component sma = new SMAImpl().newComponent()      
        IEnvironment environment = sma.environmentService()
        sma.createAgent().createTeacherAgent("molag bal")
        sma.createAgent().createTeacherAgent("azura")
        sma.createAgent().createTeacherAgent("mehrunes dagon")

        when: "the system is running"
        sma.strategyService().doStep()
        sleep(2000)
        
        then: "there are 3 allocationsTeacherRoom and each teacher has a booked room for himself"
        environment.getAllocationsTeacherRoom().size() == 3
//        environment.getAllocationsTeacherRoom().containsValue(teacherAgent1) && environment.getAllocationsTeacherRoom().containsValue(teacherAgent2) && environment.getAllocationsTeacherRoom().containsValue(teacherAgent3)
    }
}
