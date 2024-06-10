using Microsoft.AspNetCore.Http.HttpResults;
using Microsoft.AspNetCore.Mvc;
using RSI_ZAD12.Models;
using RSI_ZAD12.Services.IServices;

namespace RSI_ZAD12.Services
{
	public class MessageService : IMessageService
    {
        private List<Message> messages = new List<Message>()
        {
            new Message(1, "Pierwsza wiadomość", "Jacek"),
            new Message(2, "Druga wiadomość", "Marek"),
            new Message(3, "Trzecia wiadomość", "Ewa")
        };

        private Dictionary<int, List<string>> comments = new()
        {
            { 1, new List<string>
                {
                    "Świetny produkt, naprawdę polecam!",
                    "Bardzo dobra jakość, spełnia moje oczekiwania.",
                    "Szybka dostawa i miła obsługa.",
                    "Trochę drogo, ale warto.",
                    "Idealny prezent dla bliskiej osoby."
                }
            },
            { 2, new List<string>
                {
                    "Produkt zgodny z opisem, jestem zadowolony.",
                    "Trochę inny kolor niż na zdjęciu, ale ok.",
                    "Funkcjonalność na wysokim poziomie.",
                    "Polecam każdemu, kto szuka czegoś solidnego.",
                    "Korzystam od tygodnia i nie mam zastrzeżeń."
                }
            },
            { 3, new List<string>
                {
                    "Miałem wątpliwości, ale niepotrzebnie.",
                    "Bardzo dobra cena w porównaniu do jakości.",
                    "Obsługa klienta pomogła mi z instalacją.",
                    "Produkt spełnił wszystkie moje wymagania.",
                    "Zdecydowanie kupię ponownie."
                }
            }
        };

        public List<Message> GetMessages(HttpRequest request)
        {
            var url = Microsoft.AspNetCore.Http.Extensions.UriHelper.GetEncodedUrl(request);
            
            var rtnValue = new List<Message>(messages);
            foreach (var message in rtnValue)
            {
                message.links = new List<Link>()
                {
                    new Link($"{url}/{message.id}", "self"),
                    new Link($"{url}/{message.id}/Comments", "comments")
                };
            }
            return rtnValue;
        }
        public Message GetMessage(int id, HttpRequest request)
        {
            var url = Microsoft.AspNetCore.Http.Extensions.UriHelper.GetEncodedUrl(request);

            var message = messages.Where(x => x.id == id).First();
            message.links = new List<Link>()
            {
                new Link($"{url}", "self"),
                new Link($"{url}/Comments", "comments")
            };
            return message;
        }
        public Message CreateMessage([FromBody] Message message)
        {
            message.id = messages.Last().id + 1;
            messages.Add(message);
            return messages.Find(x => x.Equals(message));
        }

        public Message EditMessage([FromBody] Message newMessage)
        {
            var editedMessageId = messages.FindIndex(x => x.id == newMessage.id);
            messages[editedMessageId] = newMessage;
            return messages[editedMessageId];
        }

        public bool DeleteMessage([FromRoute] int id)
        {
            if (messages.Find(x => x.id == id) != null)
            {
                messages.Remove(messages.FirstOrDefault(x => x.id == id));
                return true;
            }
            return false;
        }

        public List<string> GetMessageComments(int id, HttpRequest request)
        {
            comments.TryGetValue(id, out List<String> messageComments);
            return messageComments;
        }
    }
}
