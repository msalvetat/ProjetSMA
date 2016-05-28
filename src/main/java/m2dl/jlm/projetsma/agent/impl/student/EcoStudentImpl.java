package m2dl.jlm.projetsma.agent.impl.student;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import sma.agent.EcoStudent;

public class EcoStudentImpl extends EcoStudent {

    @Override
    protected StudentS make_StudentS(final String id) {
        return new StudentS() {

            @Override
            protected ITwoStepsAgent make_student() {
                ITwoStepsAgent student = new Student(id, requires().knowledge(),
                    eco_requires().messagingService());
                eco_requires().strategyService().addAgent(student);
                return student;
            }
        };
    }
}
