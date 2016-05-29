package m2dl.jlm.projetsma.agent.student.knowledge;

import sma.ecoKnowledgeStudent.EcoKnowledgeStudent;

public class EcoKnowledgeStudentImpl extends EcoKnowledgeStudent {

    @Override
    protected KnowledgeStudentS make_KnowledgeStudentS(String id) {
        return new KnowledgeStudentS() {

            @Override
            protected IKnowledgeStudent make_knowledge() {
                return new KnowledgeStudent();
            }
            
        };
    }
}
