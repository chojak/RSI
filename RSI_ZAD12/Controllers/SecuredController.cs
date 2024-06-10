using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace RSI_ZAD12.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class SecuredController : ControllerBase
    {
        [HttpGet]
        public IActionResult CheckIfLogged() 
        {
            return Ok("Login successful");
        }
    }
}
