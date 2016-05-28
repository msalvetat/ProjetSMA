package m2dl.jlm.projetsma.agent

import m2dl.jlm.projetsma.agent.impl.EcoAgentsImpl
import m2dl.jlm.projetsma.environment.IEnvironment
import m2dl.jlm.projetsma.environment.impl.EnvironmentImpl
import m2dl.jlm.projetsma.impl.SMAImpl
import sma.EcoAgents
import sma.SMA
import sma.EcoAgents.Teacher
import spock.lang.Specification
import fr.irit.smac.libs.tooling.messaging.AgentMessaging
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent

class SmaComponentsTest extends Specification {

    def 'check if an agent can be created'() {

        given:
        SMA.Component sma = new SMAImpl().newComponent()

        when:
        ICreateAgent agent = sma.createAgent()
        Teacher teacher = sma.createAgent().createTeacherAgent("Teacher1")

        then:
        sma.systemStrategy().getAgents().size() == 1
    }


    def 'check if the environment is correctly initialised'() {

        when:
        SMA.Component sma = new SMAImpl().newComponent()

        then:
        IEnvironment environment = sma.environmentService()
        environment.getRooms().size() == 3
    }
}
