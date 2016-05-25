package m2dl.jlm.projetsma.agent

import java.util.concurrent.Executors

import m2dl.jlm.projetsma.agent.impl.TeacherAgent;
import m2dl.jlm.projetsma.agent.knowledge.Knowledge
import m2dl.jlm.projetsma.environment.impl.EnvironmentImpl;
import m2dl.jlm.projetsma.environment.impl.Room;
import spock.lang.Ignore
import spock.lang.Specification
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent
import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.TwoStepsSystemStrategy

class TeacherAgentTest extends Specification {

	def 'check the step perceive and decideAndAct for a teacher agent'() {

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
		// Step decideAndAct make that 1 room has been booked by sheogorath
		knowledge.getAllocationsTeacherRoom().size() == 1
	}

	def 'check behaviours for 3 agents'() {

		given: "3 rooms and 3 teachers"

        // Reset instance
        EnvironmentImpl.getInstance().setAllocationsTeacherRoom(new HashMap<Room,TeacherAgent>())

		TwoStepsSystemStrategy systemStrategy = new TwoStepsSystemStrategy(new HashSet<ITwoStepsAgent>(),Executors.newFixedThreadPool(8));
		TeacherAgent teacher1 = new TeacherAgent("sheogorath");
		TeacherAgent teacher2 = new TeacherAgent("azura");
		TeacherAgent teacher3 = new TeacherAgent("mehrunes dagon");

		def iterator = EnvironmentImpl.getInstance().getRooms().iterator()

		systemStrategy.addAgent(teacher1);
		systemStrategy.addAgent(teacher2);
		systemStrategy.addAgent(teacher3);

		when: "the system is running"
		systemStrategy.doStep();

		then: "there are 3 allocationsTeacherRoom and each teacher has a booked room for himself"
		TeacherAgent teacherSMA = systemStrategy.getAgents().first()
		Knowledge knowledge = teacherSMA.getKnowledge()
		knowledge.getAllocationsTeacherRoom().size() == 3
        // Testing allocates rooms
        knowledge.getAllocationsTeacherRoom().containsValue(teacher1) && knowledge.getAllocationsTeacherRoom().containsValue(teacher2) && knowledge.getAllocationsTeacherRoom().containsValue(teacher3)
	}
	
}
