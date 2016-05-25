package m2dl.jlm.projetsma.agent

import m2dl.jlm.projetsma.agent.impl.EcoAgentsImpl
import m2dl.jlm.projetsma.agent.impl.TeacherAgent
import m2dl.jlm.projetsma.agent.impl.TeacherImpl
import m2dl.jlm.projetsma.environment.IEnvironment
import m2dl.jlm.projetsma.environment.impl.EnvironmentImpl
import m2dl.jlm.projetsma.impl.SMAImpl
import sma.EcoAgents
import sma.Environment
import sma.SMA
import spock.lang.Specification

class EcoAgentsTest extends Specification {

    def 'check if the ecoAgents is correctly initialised'() {

        when:
        EcoAgents.Component ecoAgents = new EcoAgentsImpl().newComponent()

        then:
        ecoAgents instanceof EcoAgents.Component
    }

    def 'check if the ecoAgents can create agents'() {

        when:
        EcoAgents.Component ecoAgents = new EcoAgentsImpl().newComponent()
        TeacherImpl.Component teacher = ecoAgents.create().createTeacherAgent("Teacher1")
        TeacherImpl.Component teacher2 = ecoAgents.create().createTeacherAgent("Teacher2")

        then:
        teacher instanceof TeacherImpl.Component && teacher2 instanceof TeacherImpl.Component
    }

    def 'check if an agent is doing a step'() {

        when:
        EcoAgents.Component ecoAgents = new EcoAgentsImpl().newComponent()
        TeacherImpl.Component teacher = ecoAgents.create().createTeacherAgent("Teacher1")
        
        SMA.Component sma = new SMAImpl().newComponent()
        sma.systemStragy().createService()
        TeacherAgent teacherAgent = teacher.agent().getTeacherAgent();
        sma.systemStragy().addAgent(teacherAgent)
        sma.systemStragy().doStep()
        
        then:
        teacher instanceof TeacherImpl.Component
    }
    
    def 'check if the environment is correctly initialised'() {
        
        when:
        Environment.Component environment = new EnvironmentImpl().newComponent()
        environment.environment().init()
        
        then:
        environment instanceof EnvironmentImpl.Component
    }
    
    def 'check if the environment is correctly initialised in all the system'() {
        
        when:
        SMA.Component sma = new SMAImpl().newComponent()
        println sma
        IEnvironment environment = sma.environmentService()
        environment.init()
        ICreateAgent agent = sma.createAgent()
        TeacherImpl.Component teacher = agent.create().createTeacherAgent("Teacher1")
        
        
        then:
        environment.getRooms().size() == 3
        environment.getEtudiants().size() == 3
    }
}
