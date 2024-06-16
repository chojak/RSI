using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using System.Globalization;
using System.Text;

namespace Consumer
{
	internal class Program
	{
		static string topic = "rsi_jms";
		static void Main(string[] args)
		{
            Console.WriteLine("Consumer started!");
			Console.WriteLine($"Listening at topic: {topic}");

			receiveMessage();
		}

		static void receiveMessage()
		{
			var factory = new ConnectionFactory() { HostName = "localhost" };
			using (var connection = factory.CreateConnection())
			using (var channel = connection.CreateModel())
			{
				channel.QueueDeclare(queue: "rsi_jms",
									 durable: false,
									 exclusive: false,
									 autoDelete: false,
									 arguments: null);


				var consumer = new EventingBasicConsumer(channel);
				consumer.Received += (model, ea) =>
				{
					var body = ea.Body.ToArray();
					var message = Encoding.UTF8.GetString(body);
					Console.WriteLine($"Received message: {message}");
				};
				channel.BasicConsume(queue: "rsi_jms",
									 autoAck: true,
									 consumer: consumer);

				while (true)
				{
					Thread.Sleep(1000);
				}
			}
		}
	}
}
