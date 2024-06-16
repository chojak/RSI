using Microsoft.AspNetCore.Mvc;
using Serwer.Models;

namespace Serwer.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ProductsController : ControllerBase
    {
        private static List<Product> Products = new List<Product>()
        {
            new() { Name = "Mleko", Producer = "Mlekowita", Price = 5 },
            new() { Name = "Chleb", Producer = "Cukiernia XYZ", Price = 3 },
            new() { Name = "Jabłko", Producer = "Sad Gruszek", Price = 2 },
            new() { Name = "Płatki", Producer = "Firma Zdrowe Ziarno", Price = 4 },
            new() { Name = "Woda", Producer = "Źródło Żywe", Price = 1 },
            new() { Name = "Laptop", Producer = "Lenovo", Price = 3000 },
            new() { Name = "Monitor", Producer = "Samsung", Price = 800 },
            new() { Name = "Klawiatura", Producer = "Logitech", Price = 150 },
            new() { Name = "Myszka", Producer = "Razer", Price = 100 },
            new() { Name = "Drukarka", Producer = "HP", Price = 250 },
        };

        [HttpGet]
        [Produces("application/xml", "application/json")]
        public List<Product> GetAllProducts([FromBody]SearchParam? searchParams)
        {
            if (searchParams is null)
                return Products;
            
            var products = new List<Product>(Products);
            
            if (searchParams.Name is string name)
                products = products
                    .Where(x => x.Name.ToLower().StartsWith(name.ToLower()))
                    .ToList();

            if (searchParams.Producer is string producer)
                products = products
                    .Where(x => x.Producer.ToLower().StartsWith(producer.ToLower()))
                    .ToList();

            if (searchParams.Price is int price)
                products = products
                    .Where(x => x.Price < price)
                    .ToList();

            return products;
        }
    }
}
