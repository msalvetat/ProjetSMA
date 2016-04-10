package m2dl.jlm.projetsma.agent

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executors

import m2dl.jlm.projetsma.agent.knowledge.Knowledge
import m2dl.jlm.projetsma.environment.Environment;
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

	def 'check the step decideAndAct for a teacher agent'() {

		given: // GIVEN 3 rooms and 1 already allocated rooms
		TwoStepsSystemStrategy systemStrategy = new TwoStepsSystemStrategy(new HashSet<ITwoStepsAgent>(),Executors.newFixedThreadPool(8));
		TeacherAgent teacher = new TeacherAgent("sheogorath");
		Room room1 = teacher.getKnowledge().getRooms().iterator().next();
		teacher.getKnowledge().getAllocationsTeacherRoom().put(room1, null);
		systemStrategy.addAgent(teacher);

		when: // WHEN the system is running
		systemStrategy.doStep();

		then:
		TeacherAgent teacherSMA = systemStrategy.getAgents().first()
		Knowledge knowledge = teacherSMA.getKnowledge()
		knowledge.getAllocationsTeacherRoom().size() == 2
		knowledge.getAllocationsTeacherRoom().get(room1) == teacher
	}
	
}
