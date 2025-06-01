using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class SessionSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Session == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Session.Any())
                {
                    return;   // DB has been seeded
                }

                context.Session.AddRange(
                    new Session
                    {
                        ShowingDate = DateTime.Parse("12/10/2023 07:00:00 +8:00"),
                        HallId = 1,
                        MovieId = 1
                    },
                    new Session
                    {
                        ShowingDate = DateTime.Parse("12/10/2023 09:00:00 +8:00"),
                        HallId = 1,
                        MovieId = 1
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Session
    {
        public int SessionId { get; set; }

        [Display(Name = "Showing Date")]
        [DataType(DataType.DateTime)]
        public DateTime ShowingDate { get; set; }

        [Display(Name = "Hall")]
        [ForeignKey("Hall")]
        public int HallId { get; set; }
        public Hall Hall { get; set; } = null!;

        [ForeignKey("Movie")]
        public int MovieId { get; set; }
        public Movie Movie { get; set; } = null!;
    }
}
