using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class HallSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Hall == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Hall.Any())
                {
                    return;   // DB has been seeded
                }

                context.Hall.AddRange(
                    new Hall
                    {
                        Type = "Deluxe",
                        BranchId = 1,
                        RowSize = 10,
                        ColumnSize = 10,
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Hall
    {
        public int HallId { get; set; }
        public string Type { get; set; } = string.Empty;

        public int RowSize { get; set; }
        public int ColumnSize { get; set; }
        public ICollection<Seat> Seats { get; set; } = null!;

        [ForeignKey("CinemaBranch")]
        public int BranchId {  get; set; }
        public Branch Branch { get; set; } = null!;
    }
}
