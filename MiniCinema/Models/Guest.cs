using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;

namespace MiniCinema.Models
{
    public static class GuestSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Guest == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Guest.Any())
                {
                    return;   // DB has been seeded
                }

                context.Guest.AddRange(
                    new Guest
                    {
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Guest
    {
        public int Id { get; set; }
        public string Email { get; set; } = string.Empty;
        public string PhoneNumber { get; set; } = string.Empty;
    }
}
