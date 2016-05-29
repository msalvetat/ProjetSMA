package m2dl.jlm.projetsma.agent.impl.teacher.knowledge.impl;

import m2dl.jlm.projetsma.agent.impl.teacher.knowledge.IKnowledgeTeacher;
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
