using System.Runtime.Serialization;
using System.Text.Json.Serialization;
using System.Xml.Serialization;

namespace RSI_ZAD9.Models
{
	[DataContractAttribute]
	public class Message
	{
		[DataMemberAttribute]
		[JsonInclude]
		public long id;
		[DataMemberAttribute]
		[JsonInclude]
		public string message;
		[DataMemberAttribute]
		[JsonInclude]
		public DateTime created;
		[DataMemberAttribute]
		[JsonInclude]
		public string author;

		public Message(long id, string message, string author)
		{
			this.id = id;
			this.message = message;
			this.created = new DateTime();
			this.author = author;
		}	
	}
}
