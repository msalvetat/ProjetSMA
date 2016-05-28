package m2dl.jlm.projetsma.agent

import m2dl.jlm.projetsma.agent.impl.teacher.Teacher;
import m2dl.jlm.projetsma.environment.IEnvironment
import m2dl.jlm.projetsma.impl.SMAImpl
import sma.SMA
import spock.lang.Specification

class SmaComponentsTest extends Specification {

    def 'check if an agent can be created'() {

        given:
        SMA.Component sma = new SMAImpl().newComponent()

        when:
        ICreateAgent agent = sma.createAgent()
        Teacher teacher = sma.createAgent().createTeacherAgent("Teacher1")

        then:
        sma.strategyService().getAgents().size() == 1
    }


    def 'check if the environment is correctly initialised'() {

        when:
        SMA.Component sma = new SMAImpl().newComponent()

        then:
        IEnvironment environment = sma.environmentService()
        environment.getRooms().size() == 3
    }
}
