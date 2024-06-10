namespace RSI_ZAD12.Middleware
{
    public class MyResponseMiddleware
    {
        private readonly RequestDelegate _next;
        public MyResponseMiddleware(RequestDelegate next)
        {
            _next = next;   
        }
        public async Task InvokeAsync(HttpContext context)
        {
            context.Request.Headers.Add("mojNaglowek", "rsi test");
            await _next(context);
        }
    }
}
