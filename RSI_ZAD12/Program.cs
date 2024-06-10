using Microsoft.AspNetCore.Authentication.Negotiate;
using RSI_ZAD12.Middleware;
using RSI_ZAD12.Services;
using RSI_ZAD12.Services.IServices;
using RSI_ZAD9.Controllers;

namespace RSI_ZAD12
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.
            builder.Services.AddControllers();
            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();

            //builder.Services.AddSingleton<IMessageService, MessageService>();
            builder.Services.AddScoped<IMessageService, MessageService>();

            var app = builder.Build();

            app.UseMiddleware<MyResponseMiddleware>();

            app.UseWhen(context => context.Request.Path.ToString().Contains("/Secured"), appBuilder =>
            {
                appBuilder.UseMiddleware<BasicAuthMiddleware>();
            });

            // Configure the HTTP request pipeline.
            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI();
            }

            app.UseHttpsRedirection();

            app.UseAuthorization();

            app.MapControllers();

            app.Run();
        }
    }
}
