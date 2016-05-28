package m2dl.jlm.projetsma.agent

import m2dl.jlm.projetsma.agent.knowledge.Knowledge
import m2dl.jlm.projetsma.environment.IEnvironment
import m2dl.jlm.projetsma.impl.SMAImpl
import sma.SMA
import sma.EcoAgents.Teacher
import spock.lang.Specification
import fr.irit.smac.libs.tooling.messaging.AgentMessaging
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class TeacherAgentTest extends Specification {
    
    def 'check the step perceive and decideAndAct for a teacher agent'() {

        given:
        SMA.Component sma = new SMAImpl().newComponent()
        IEnvironment environment = sma.environmentService()
        environment.init()
        ICreateAgent agent = sma.createAgent()
        Teacher teacher = agent.createTeacherAgent("sheogorath")
        ITwoStepsAgent teacherAgent = teacher.make_agent()
        sma.systemStrategy().init()
        sma.systemStrategy().addAgent(teacherAgent)

        when:
        sma.systemStrategy().doStep()
        sleep(1000)

        then:
        environment.getRooms().size() == 3
        // Step decideAndAct make that 1 room has been booked by sheogorath
        environment.getAllocationsTeacherRoom().size() == 1
        sma.systemStrategy().shutdown()
    }

    def 'check behaviours for 3 agents'() {

        given: "3 rooms and 3 teachers"
        SMA.Component sma = new SMAImpl().newComponent()
        IEnvironment environment = sma.environmentService()
        environment.init()
        ICreateAgent agent = sma.createAgent()
        Teacher teacher = agent.createTeacherAgent("molag bal")
        Teacher teacher2 = agent.createTeacherAgent("azura")
        Teacher teacher3 = agent.createTeacherAgent("mehrunes dagon")
        ITwoStepsAgent teacherAgent1 = teacher.make_agent()
        ITwoStepsAgent teacherAgent2 = teacher2.make_agent()
        ITwoStepsAgent teacherAgent3 = teacher3.make_agent()
        sma.systemStrategy().init()
        sma.systemStrategy().addAgent(teacherAgent1)
        sma.systemStrategy().addAgent(teacherAgent2)
        sma.systemStrategy().addAgent(teacherAgent3)

        when: "the system is running"
        sma.systemStrategy().doStep()
        sleep(2000)
        
        then: "there are 3 allocationsTeacherRoom and each teacher has a booked room for himself"
        environment.getAllocationsTeacherRoom().size() == 3
        environment.getAllocationsTeacherRoom().containsValue(teacherAgent1) && environment.getAllocationsTeacherRoom().containsValue(teacherAgent2) && environment.getAllocationsTeacherRoom().containsValue(teacherAgent3)
    }
}
