package m2dl.jlm.projetsma.services;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import m2dl.jlm.projetsma.services.impl.Message;

public interface IMessagingService {

    public IMsgBox<Message> getMsgBox(String agentId, Class<?> messageClassType);

    public boolean sendToGroup(Message msg, String groupId, String agentId);
}
