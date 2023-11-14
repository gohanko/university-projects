using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using MiniCinema.Data;
using MiniCinema.Models;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddRazorPages();
builder.Services.AddDbContext<MiniCinemaContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("MiniCinemaContext") ?? throw new InvalidOperationException("Connection string 'MiniCinemaContext' not found.")));

var app = builder.Build();

using (var scope = app.Services.CreateScope())
{
    var services = scope.ServiceProvider;

    LocationAddressSeedData.Initialize(services);
    BranchSeedData.Initialize(services);
    
    SeatTypeSeedData.Initialize(services);
    SeatingConfigurationSeedData.Initialize(services);
    SeatSeedData.Initialize(services);
    HallSeedData.Initialize(services);
    
    GuestSeedData.Initialize(services);
    MovieDetailSeedData.Initialize(services);
    MovieSessionSeedData.Initialize(services);
    /*TicketSeedData.Initialize(services);
    TransactionSeedData.Initialize(services);
    TransactionTypeSeedData.Initialize(services);*/
}

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapRazorPages();

app.Run();
