using System.Text;

namespace RSI_ZAD12.Middleware
{
    public class BasicAuthMiddleware
    {
        private readonly RequestDelegate _next;
        public BasicAuthMiddleware(RequestDelegate next)
        {
            _next = next;
        }
        public async Task InvokeAsync(HttpContext context)
        {
            string login = "";
            string password = "";
            if (context.Request.Headers.TryGetValue("Authorization", out var basicAuthCredentials))
            {
                var credentials = basicAuthCredentials.ToString().Split(" ")[1];
                var decodedCredentials = Encoding.UTF8.GetString(Convert.FromBase64String(credentials)).Split(":");
                login = decodedCredentials[0];
                password = decodedCredentials[1];
            }
            
            if (login == "dobry" && password == "user")
            {
                await _next(context);
                return;
            }

            context.Response.ContentType = "text/plain";
            context.Response.StatusCode = 401;
            await context.Response.WriteAsync("Invalid user credentials");
        }
    }
}
