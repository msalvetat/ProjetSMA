package m2dl.jlm.projetsma.agent

import java.util.concurrent.Executors

import m2dl.jlm.projetsma.agent.knowledge.Knowledge
import m2dl.jlm.projetsma.environment.Room
import spock.lang.Specification
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.TwoStepsSystemStrategy

class TeacherAgentTest extends Specification {

	def 'check the step perceive for a teacher agent'() {

		given:
		TwoStepsSystemStrategy systemStrategy = new TwoStepsSystemStrategy(new HashSet<ITwoStepsAgent>(),Executors.newFixedThreadPool(8));
		TeacherAgent teacher = new TeacherAgent("sheogorath");
		systemStrategy.addAgent(teacher);

		when:
		systemStrategy.doStep();

		then:
		TeacherAgent teacherSMA = systemStrategy.getAgents().first()
		Knowledge knowledge = teacherSMA.getKnowledge()
		knowledge.getRooms().size() == 3
		knowledge.getAllocationsTeacherRoom().size() == 0
	}
	
}
