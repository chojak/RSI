using RabbitMQ.Client;
using System.Text;

namespace Producer
{
	internal class Program
	{
		static string topic = "rsi_jms";
		static void Main(string[] args)
		{
			int counter = 0;
			Console.WriteLine("Producer started!");
			Console.WriteLine($"Producing at topic: {topic}");
			//while (true) 
			//{ 
			//	sendMessage((counter++).ToString());
			//             Thread.Sleep(2000);
			//}

			while (true)
			{
                Console.Write("Enter message: ");
                sendMessage(Console.ReadLine() ?? "");
			}
		}
		static void sendMessage(string inputMessage)
		{
			var factory = new ConnectionFactory() { HostName = "localhost" };
			using (var connection = factory.CreateConnection())
			using (var channel = connection.CreateModel())
			{
				channel.QueueDeclare(queue: topic,
									 durable: false,
									 exclusive: false,
									 autoDelete: false,
									 arguments: null);

				string message = $"{inputMessage}";
				var body = Encoding.UTF8.GetBytes(message);

				channel.BasicPublish(exchange: "",
									 routingKey: topic,
									 basicProperties: null,
									 body: body);

				Console.WriteLine($"Message published: {message}");
			}
		}
	}
}
