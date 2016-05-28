package m2dl.jlm.projetsma.agent.knowledge;

import m2dl.jlm.projetsma.agent.IKnowledge;
import sma.knowledge.EcoKnowledge;

public class EcoKnowledgeImpl extends EcoKnowledge {

    @Override
    protected KnowledgeS make_KnowledgeS(String id) {
        return new KnowledgeS() {

            @Override
            protected IKnowledge make_knowledge() {
                return new Knowledge(eco_requires().environment());
            }
        };
    }
}
