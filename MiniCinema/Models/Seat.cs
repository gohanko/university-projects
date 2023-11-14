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
                        IsDisabled = false,
                        SeatTypeId = 1,
                        SeatingConfigurationId = 1,
                    },
                    new Seat
                    {
                        RowStart = 1,
                        ColumnStart = 2,
                        RowEnd = 1,
                        ColumnEnd = 2,
                        IsDisabled = false,
                        SeatTypeId = 1,
                        SeatingConfigurationId = 1,
                    },
                    new Seat
                    {
                        RowStart = 1,
                        ColumnStart = 3,
                        RowEnd = 1,
                        ColumnEnd = 3,
                        IsDisabled = false,
                        SeatTypeId = 1,
                        SeatingConfigurationId = 1,
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
        public bool IsDisabled { get; set; }

        [ForeignKey("SeatType")]
        public int SeatTypeId { get; set; }
        public SeatType SeatType { get; set; } = null!;

        [ForeignKey("SeatingConfiguration")]
        public int SeatingConfigurationId { get; set; }
        public SeatingConfiguration SeatingConfiguration { get; set; } = null!;
    }
}
