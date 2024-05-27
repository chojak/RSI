using Microsoft.AspNetCore.Mvc;
using RSI_ZAD9.Models;
using RSI_ZAD9.Services;

namespace RSI_ZAD9.Controllers
{
	[ApiController]
	[Route("[controller]")]
	public class MessagesController : ControllerBase
	{
		[HttpGet]
        [Produces("application/xml", "application/json")]
		public List<Message> GetAllMessages()
		{
			return MessageService.GetMessages();
		}

		[HttpGet("[action]")]
		[Produces("application/xml", "application/json")]
		public Message GetMessage([FromQuery] int id)
		{
			return MessageService.GetMessage(id);
		}

		[HttpPost]
		[Produces("application/xml", "application/json")]
		public Message CreateMessage([FromBody] Message message)
		{
			return MessageService.CreateMessage(message);
		}

		[HttpPut]
		[Produces("application/xml", "application/json")]
		public Message EditMessage([FromBody] Message message)
		{
			return MessageService.EditMessage(message);
		}

		[HttpDelete]
		[Produces("application/xml", "application/json")]
		public ActionResult DeleteMessage([FromHeader] int id)
		{
			if (MessageService.DeleteMessage(id))
				return Ok();
			return NotFound();
		}
	}
}
