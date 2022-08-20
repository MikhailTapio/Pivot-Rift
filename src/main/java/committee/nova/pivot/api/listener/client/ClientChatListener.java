package committee.nova.pivot.api.listener.client;

import committee.nova.pivot.eventInfo.ClientChatInfo;

public interface ClientChatListener {
    ClientChatInfo onClientChat(ClientChatInfo info);
}
