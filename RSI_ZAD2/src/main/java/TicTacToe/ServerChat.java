package TicTacToe;

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
    public void checkConnection() throws RemoteException {
        System.out.println("Server responded to checkConnection");
    }
    @Override
    public void sendMessage(ChatUserInterface sender, String message) throws RemoteException {
        String finalMessage = createMessage(sender, message);
        if (!sender.getName().equals("SYSTEM"))
            System.out.println(MessageFormat.format("{0} send an message: {1}", sender.getName(), message));
        for (ChatUserInterface user : chatUsers) {
            if (!user.getName().equals(sender.getName()))
                user.receiveMessage(finalMessage);
        }
    }

    @Override
    public String disconnect(ChatUserInterface user) throws RemoteException {
        boolean removed = chatUsers.removeIf(usr -> {
            try {
                return usr.getName().equals(user.getName());
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        });
        if (removed) {
            String rtn = MessageFormat.format("{0} left the server ", user.getName());
            sendMessage(new ChatUser("SYSTEM"), rtn);
            System.out.println(rtn);
            return "Disconnected from server";
        }
        else {
            return "Something went wrong";
        }
    }

    private String createMessage(ChatUserInterface sender, String message) throws RemoteException {
        if (message.equals("exit")) {
            return MessageFormat.format("SYSTEM: {0} disconnected", sender.getName());
        }
        return MessageFormat.format("[{0}]: {1}", sender.getName(), message);
    }
}