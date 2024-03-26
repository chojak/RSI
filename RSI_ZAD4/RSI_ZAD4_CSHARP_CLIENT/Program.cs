// See https://aka.ms/new-console-template for more information

using ServiceReference1;
using System.Text;

var client = new ServerHelloWorldClient();

Console.WriteLine("Podaj imie: ");
var input = Console.ReadLine();
var response = await client.getHelloWorldAsStringAsync(input);

Console.WriteLine(response.@return + "\n");

var dostanProduktyResponse = await client.DostanProduktyAsync();
var responseValue = new StringBuilder();
dostanProduktyResponse.@return.ToList().ForEach(x => responseValue.AppendLine($"Nazwa: {x.nazwa}, opis: {x.opis}, cena: {x.cena}"));

Console.WriteLine("Lista produktów: \n" + responseValue);
