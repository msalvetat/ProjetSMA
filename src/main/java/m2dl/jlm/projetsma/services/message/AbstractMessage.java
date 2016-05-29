package m2dl.jlm.projetsma.services.message;

public class AbstractMessage {

    public EMessageType messageType;

    public EMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(EMessageType messageType) {
        this.messageType = messageType;
    }
}
