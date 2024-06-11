using Klient.Models;
using System.Net.Http.Json;
using System.Text.Json;

namespace Klient
{
    internal class Program
    {
        static HttpClient client = new HttpClient();
        static async Task Main(string[] args)
        {
            string url = @"https://26.28.182.188:7138/Product";

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

            GetProducts();
        }

        static async Task GetProducts()
        {
            await Console.Out.WriteLineAsync("Wyświetlam wszystkie produkty:");
            var response = client.GetFromJsonAsync<List<Product>>("").Result;
            foreach (var item in response)
            {
                Console.WriteLine(item.Name);
            }
        }
    }
}
