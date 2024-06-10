using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RSI_ZAD11
{
    public class Message
    {
        public int id { get; set; }
        public string message { get; set; }
        public DateTime created { get; set; }
        public string author { get; set; }
        public Message() { }
        public Message(int id, string message, string author)
        {
            this.id = id;
            this.message = message;
            this.created = new DateTime();
            this.author = author;
        }
        public Message(int id, string message, DateTime created, string author)
        {
            this.id = id;
            this.message = message;
            this.created = created;
            this.author = author;
        }
    }
}
