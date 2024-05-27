using Microsoft.AspNetCore.Mvc;
using RSI_ZAD9.Models;

namespace RSI_ZAD9.Services
{
	public static class MessageService
	{
        private static List<Message> messages = new List<Message>()
        {
            new Message(1, "Pierwsza wiadomość", "Jacek"),
            new Message(2, "Druga wiadomość", "Marek"),
            new Message(3, "Trzecia wiadomość", "Ewa")
        };
        public static List<Message> GetMessages()
        {
            return messages;
        }
        public static Message GetMessage(int id)
        {
            return messages.Where(x => x.id == id).First();
        }
        public static Message CreateMessage([FromBody] Message message)
        {
            messages.Add(message);
            return messages.Find(x => x.Equals(message));
        }

        public static Message EditMessage([FromBody] Message newMessage)
        {
            var editedMessageId = messages.FindIndex(x => x.id == newMessage.id);
            messages[editedMessageId] = newMessage;
            return messages[editedMessageId];
        }

        public static bool DeleteMessage([FromRoute] int id)
        {
            if (messages.Find(x => x.id == id) != null)
            {
                messages.RemoveAt(id);
                return true;
            }
            return false;
        }
    }
}
