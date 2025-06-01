using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;

namespace MiniCinema.Models
{
    public static class AddressSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Address == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Address.Any())
                {
                    return;   // DB has been seeded
                }

                context.Address.AddRange(
                    new Address
                    {
                        Line1 = "Lot 554, 5",
                        Line2 = "Jalan Baru, Taman Tasik Emas",
                        TownCity = "Kampar",
                        StateProvince = "Perak",
                        CountryCode = "MY",
                        PostalCode = "31900",
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Address
    {
        public int AddressId { get; set; }
        public string Line1 { get; set; } = string.Empty;
        public string Line2 { get; set; } = string.Empty;
        public string TownCity { get; set; } = string.Empty;
        public string StateProvince { get; set; } = string.Empty;
        public string CountryCode { get; set; } = string.Empty;
        public string PostalCode { get; set; } = string.Empty;
    }
}
