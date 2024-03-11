package Chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

public class ServerChat extends UnicastRemoteObject implements ServerChatInterface {
    protected ServerChat() throws RemoteException {
        super();
    }
    private static final Collection<ChatUserInterface> chatUsers = new ArrayList<>();

    @Override
    public String connect(ChatUserInterface user) throws RemoteException {
        chatUsers.add(user);
        String message = MessageFormat.format("{0} joined the channel", user.getName());
        System.out.println(message);
        sendMessage(new ChatUser("SYSTEM"), message);
        return "Joined";
    }

    @Override
    public String checkConnection() throws RemoteException {
        return "Server responds";
    }

    @Override
    public boolean sendMessage(ChatUserInterface sender, String message) throws RemoteException {
        String finalMessage = createMessage(sender, message);
        for (ChatUserInterface user : chatUsers) {
            if (!user.getName().equals(sender.getName()))
                user.receiveMessage(finalMessage);
        }
        return false;
    }

    private String createMessage(ChatUserInterface sender, String message) throws RemoteException {
        if (message.equals("exit")) {
            return MessageFormat.format("SYSTEM: {0} disconnected", sender.getName());
        }
        return MessageFormat.format("[{0}]: {1}", sender.getName(), message);
    }
}