package m2dl.jlm.projetsma.agent.teacher;

import fr.irit.smac.libs.tooling.scheduling.contrib.twosteps.ITwoStepsAgent;
import sma.agent.EcoTeacher;

public class EcoTeacherImpl extends EcoTeacher {

    @Override
    protected TeacherS make_TeacherS(final String id) {

        return new TeacherS() {

            @Override
            protected ITwoStepsAgent make_teacher() {
                ITwoStepsAgent teacher = new Teacher(id, requires().knowledge(),
                    eco_requires().messagingService());
                eco_requires().strategyService().addAgent(teacher);
                return teacher;
            }
        };
    }
}
