package committee.nova.pivot.eventInfo;

public class ClientChatInfo {
    private final String original;
    private String msg;
    private boolean addToChat;

    public ClientChatInfo(String original, String msg, boolean addToChat) {
        this.original = original;
        this.msg = msg;
        this.addToChat = addToChat;
    }

    public String getOriginal() {
        return original;
    }

    public String getMsg() {
        return msg;
    }

    public boolean willAddToChat() {
        return addToChat;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setShouldAddToChat(boolean addToChat) {
        this.addToChat = addToChat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientChatInfo that = (ClientChatInfo) o;
        return addToChat == that.addToChat && msg.equals(that.msg);
    }
}
