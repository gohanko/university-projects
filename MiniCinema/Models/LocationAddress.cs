using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;

namespace MiniCinema.Models
{
    public static class LocationAddressSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.LocationAddress == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.LocationAddress.Any())
                {
                    return;   // DB has been seeded
                }

                context.LocationAddress.AddRange(
                    new LocationAddress
                    {
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class LocationAddress
    {
        public int Id { get; set; }
        public string Line1 { get; set; } = string.Empty;
        public string Line2 { get; set; } = string.Empty;
        public string TownCity { get; set; } = string.Empty;
        public string StateProvince { get; set; } = string.Empty;
        public string CountryCode { get; set; } = string.Empty;
        public string PostalCode { get; set; } = string.Empty;
    }
}
