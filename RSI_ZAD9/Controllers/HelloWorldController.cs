using Microsoft.AspNetCore.Mvc;

namespace RSI_ZAD9.Controllers
{
	[ApiController]
	[Route("hello")]
	public class HelloWorldController : ControllerBase
	{
		[HttpGet]
		//[Produces("")]
		public string Get()
		{
			return "Witaj JAX-RS";
		}

		[HttpGet("echo")]
		public string GetEcho()
		{
			return "Witaj echo";
		}

		[HttpGet("echo2/{name}")]
		public string GetEcho([FromRoute]string name)
		{
			return $"Witaj echo: {name}";
		}
	}
}
