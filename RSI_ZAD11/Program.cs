
using System.Net.Http.Json;
using System.Text.Json;

namespace RSI_ZAD11
{
    internal class Program
    {
        static HttpClient client = new HttpClient();
        static async Task Main(string[] args)
        {
            string url = @"https://26.28.182.188:7243/messages/";

			var handler = new HttpClientHandler();
			handler.ClientCertificateOptions = ClientCertificateOption.Manual;
			handler.ServerCertificateCustomValidationCallback = 
				(httpRequestMessage, cert, cetChain, policyErrors) =>
				{
					return true;
				};
		
            client = new HttpClient(handler)
            {
                BaseAddress = new Uri(url)
            };

            await GetMessages();

            await PostMessage(new Message() { author = "Klient apliakcji w .net", message = "Super wszystko działa"} );

            await GetMessage();

            await EditMessage(new Message(4, "Nowa wiadomość", "Klient aplikacji w c# "));
            
            await GetMessage();

            await DeleteMessage();
            
            await GetMessages();
        }

        static async Task GetMessages()
        {
            await Console.Out.WriteLineAsync("\nWyświetlam wszystkie wiadomości:");
            var response = client.GetFromJsonAsync<List<Message>>("").Result;
            foreach (var item in response)
            {
                Console.WriteLine(JsonSerializer.Serialize(item));
            }
        }

        static async Task GetMessage()
        {
            await Console.Out.WriteLineAsync("\nPodaj ID wiadomości którą chcesz wyświetlić: ");
            var response = client.GetFromJsonAsync<Message>($"GetMessage/?id={int.Parse(Console.ReadLine())}").Result;
            Console.WriteLine(JsonSerializer.Serialize(response));
        }

        static async Task PostMessage(Message newMessage)
        {
            await Console.Out.WriteLineAsync("\nDodaję nową wiadomość.");
            var response = client.PostAsJsonAsync("", newMessage).Result.Content.ReadFromJsonAsync<Message>().Result;
            Console.WriteLine(JsonSerializer.Serialize(response));
        }

        static async Task EditMessage(Message newMessage)
        {
            await Console.Out.WriteLineAsync("\nEdutuję wiadomość.");
            var response = client.PutAsJsonAsync("", newMessage).Result.Content.ReadFromJsonAsync<Message>().Result;
            Console.WriteLine(JsonSerializer.Serialize(response));
        }

        static async Task DeleteMessage()
        {
            await Console.Out.WriteLineAsync("\nPodaj ID wiadomości do usunięcia: ");
            var request = new HttpRequestMessage(HttpMethod.Delete, client.BaseAddress);
            request.Headers.Add("id", int.Parse(Console.ReadLine()).ToString());
            var response = client.SendAsync(request).Result.StatusCode;
            Console.WriteLine(JsonSerializer.Serialize($"Response code: {response}"));
        }
    }
}
