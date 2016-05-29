package m2dl.jlm.projetsma.agent.teacher.knowledge;

import sma.ecoKnowledgeTeacher.EcoKnowledgeTeacher;

public class EcoKnowledgeTeacherImpl extends EcoKnowledgeTeacher{

    @Override
    protected KnowledgeTeacherS make_KnowledgeTeacherS(String id) {
        return new KnowledgeTeacherS() {

            @Override
            protected IKnowledgeTeacher make_knowledge() {
                return new KnowledgeTeacher();
            }
        };
    }
}
