using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class SeatSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Seat == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Seat.Any())
                {
                    return;   // DB has been seeded
                }

                context.Seat.AddRange(
                    new Seat
                    {
                        RowStart = 1,
                        ColumnStart = 1,
                        RowEnd = 1,
                        ColumnEnd = 1,
                        Capacity = 1,
                        Type = 1,
                        HallId = 1,
                    },
                    new Seat
                    {
                        RowStart = 1,
                        ColumnStart = 2,
                        RowEnd = 1,
                        ColumnEnd = 2,
                        Capacity = 1,
                        Type = 1,
                        IsAccessible = false,
                        HallId = 1,
                    },
                    new Seat
                    {
                        RowStart = 1,
                        ColumnStart = 3,
                        RowEnd = 1,
                        ColumnEnd = 3,
                        Capacity = 1,
                        Type = 1,
                        IsAccessible = false,
                        HallId = 1,
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Seat
    {
        public int SeatId { get; set; }
        public int RowStart { get; set; }
        public int ColumnStart { get; set; }
        public int RowEnd { get; set; }
        public int ColumnEnd { get; set; }
        public int Capacity { get; set; }

        public int Type { get; set; }
        public bool IsAccessible { get; set; }

        [ForeignKey("Guest")]
        public int? GuestId { get; set; }
        public Guest? Guest { get; set; } = null!;

        [ForeignKey("Hall")]
        public int HallId { get; set; }
        public Hall Hall { get; set; } = null!;
    }
}
