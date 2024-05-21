using RSI_ZAD9.Models;

namespace RSI_ZAD9.Services
{
	public class MessageService
	{
        private List<Message> messages = new List<Message>();
        public MessageService()
        {
            messages.Add(new Message(1, "Pierwsza wiadomość", "Jacek"));    
            messages.Add(new Message(2, "Druga wiadomość", "Marek"));
            messages.Add(new Message(3, "Trzecia wiadomość", "Ewa"));
		}

        public List<Message> GetMessages()
        {
            return messages;
        }
	}
}
