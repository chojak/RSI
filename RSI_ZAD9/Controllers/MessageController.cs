using Microsoft.AspNetCore.Mvc;
using RSI_ZAD9.Models;
using RSI_ZAD9.Services;

namespace RSI_ZAD9.Controllers
{
	[ApiController]
	[Route("/message")]
	public class MessageController : ControllerBase
	{
		MessageService _service = new MessageService();

		[HttpGet]
		[Produces("application/xml", "application/json")]
		public List<Message> getText()
		{
			return _service.GetMessages();
		}
	}
}
