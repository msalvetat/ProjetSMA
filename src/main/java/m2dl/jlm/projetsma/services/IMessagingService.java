package m2dl.jlm.projetsma.services;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;

public interface IMessagingService {

    public IMsgBox getMsgBox(String agentId, Class<?> messageClassType);
}
