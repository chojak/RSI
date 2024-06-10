using Microsoft.AspNetCore.Mvc;
using RSI_ZAD12.Models;
using RSI_ZAD12.Services;
using RSI_ZAD12.Services.IServices;

namespace RSI_ZAD9.Controllers
{
	[ApiController]
	[Route("[controller]")]
	public class MessagesController : ControllerBase
	{
		IMessageService messageService;
        public MessagesController(IMessageService messageService)
        {
			this.messageService = messageService;
        }

        [HttpGet]
        [Produces("application/xml", "application/json")]
		public List<Message> GetAllMessages()
		{
			return messageService.GetMessages(Request);
		}

		[HttpGet("{id}")]
		[Produces("application/xml", "application/json")]
		public Message GetMessage(int id)
		{
			return messageService.GetMessage(id, Request);
		}

        [HttpGet("{id}/Comments")]
        [Produces("application/xml", "application/json")]
        public ActionResult<List<string>> GetComments(int id)
        {
			var messages = messageService.GetMessageComments(id, Request);
			if (messages == null || messages.Count == 0)
				return NotFound("No comments found");
            return Ok(messageService.GetMessageComments(id, Request));
        }

        [HttpPost]
		[Produces("application/xml", "application/json")]
		public Message CreateMessage([FromBody] Message message)
		{
			return messageService.CreateMessage(message);
		}

		[HttpPut]
		[Produces("application/xml", "application/json")]
		public Message EditMessage([FromBody] Message message)
		{
			return messageService.EditMessage(message);
		}

		[HttpDelete]
		[Produces("application/xml", "application/json")]
		public ActionResult DeleteMessage([FromHeader] int id)
		{
			if (messageService.DeleteMessage(id))
				return Ok();
			return NotFound();
		}
	}
}
