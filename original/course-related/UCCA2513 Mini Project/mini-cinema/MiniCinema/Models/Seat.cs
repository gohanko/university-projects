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
                        Row = 1,
                        Column = 1,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 1,
                        Column = 2,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 1,
                        Column = 3,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 1,
                        Column = 4,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 1,
                        Column = 5,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 2,
                        Column = 1,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 2,
                        Column = 2,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 2,
                        Column = 3,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 2,
                        Column = 4,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 2,
                        Column = 5,
                        HallId = 1,
                    }, new Seat
                    {
                        Row = 3,
                        Column = 1,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 3,
                        Column = 2,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 3,
                        Column = 3,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 3,
                        Column = 4,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 3,
                        Column = 5,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 4,
                        Column = 1,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 4,
                        Column = 2,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 4,
                        Column = 3,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 4,
                        Column = 4,
                        HallId = 1,
                    },
                    new Seat
                    {
                        Row = 4,
                        Column = 5,
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
        public int Row { get; set; }
        public int Column { get; set; }

        [ForeignKey("Profile")]
        public int? ProfileId { get; set; }
        public Profile booked_by { get; set; } = null!;

        [ForeignKey("Hall")]
        public int HallId { get; set; }
        public Hall Hall { get; set; } = null!;
    }
}
