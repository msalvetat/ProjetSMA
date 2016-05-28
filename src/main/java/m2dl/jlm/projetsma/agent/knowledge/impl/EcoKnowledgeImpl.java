package m2dl.jlm.projetsma.agent.knowledge.impl;

import m2dl.jlm.projetsma.agent.knowledge.IKnowledge;
import sma.ecoKnowledge.EcoKnowledge;

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
