using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;

namespace MiniCinema.Models
{
    public static class SeatTypeSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.SeatType == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                if (context.SeatType.Any())
                {
                    return;   // DB has been seeded
                }

                context.SeatType.AddRange(
                    new SeatType
                    {
                        Name = "Standard",
                        Capacity = 1,
                        IsAccessible = false,
                    },
                    new SeatType
                    {
                        Name = "Standard Accesible",
                        Capacity = 1,
                        IsAccessible = true,
                    },
                    new SeatType
                    {
                        Name = "Deluxe Single",
                        Capacity = 1,
                        IsAccessible = false,
                    },
                    new SeatType
                    {
                        Name = "Deluxe Double",
                        Capacity = 2,
                        IsAccessible = false,
                    }
                ); ;

                context.SaveChanges();
            }
        }
    }
    public class SeatType
    {
        public int Id { get; set; }
        public string Name { get; set; } = string.Empty;
        public int Capacity { get; set; }
        public bool IsAccessible { get; set; }

        public ICollection<Seat> Seats { get; set; }
    }
}
