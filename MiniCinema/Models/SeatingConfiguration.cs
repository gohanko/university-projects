using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;

namespace MiniCinema.Models
{
    public static class SeatingConfigurationSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.SeatingConfiguration == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.SeatingConfiguration.Any())
                {
                    return;   // DB has been seeded
                }

                context.SeatingConfiguration.AddRange(
                    new SeatingConfiguration
                    {
                        RowSize = 20,
                        ColumnSize = 20,
                    },
                    new SeatingConfiguration
                    {
                        RowSize = 20,
                        ColumnSize = 10,
                    },
                    new SeatingConfiguration
                    {
                        RowSize = 35,
                        ColumnSize = 15,
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class SeatingConfiguration
    {
        public int Id { get; set; }
        public int RowSize { get; set; }
        public int ColumnSize { get; set; }

        public ICollection<Seat>? Seats { get; set; }
    }
}
