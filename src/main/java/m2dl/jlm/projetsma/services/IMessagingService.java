package m2dl.jlm.projetsma.services;

import fr.irit.smac.libs.tooling.messaging.IMsgBox;
import m2dl.jlm.projetsma.services.message.AbstractMessage;
import m2dl.jlm.projetsma.services.message.RoomPropositionMessage;

public interface IMessagingService {

    public IMsgBox<AbstractMessage> getMsgBox(String agentId, Class<?> messageClassType);

    public boolean sendToGroup(RoomPropositionMessage msg, String groupId, String agentId);
}
