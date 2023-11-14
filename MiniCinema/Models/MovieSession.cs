using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class MovieSessionSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.MovieSession == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.MovieSession.Any())
                {
                    return;   // DB has been seeded
                }

                context.MovieSession.AddRange(
                    new MovieSession
                    {
                        ShowingDate = DateTime.Parse("12/10/2023 07:00:00 +8:00"),
                        HallId = 1,
                        MovieDetailId = 1
                    },
                    new MovieSession
                    {
                        ShowingDate = DateTime.Parse("12/10/2023 09:00:00 +8:00"),
                        HallId = 1,
                        MovieDetailId = 1
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class MovieSession
    {
        public int MovieSessionId { get; set; }

        [Display(Name = "Showing Date")]
        [DataType(DataType.DateTime)]
        public DateTime ShowingDate { get; set; }

        [ForeignKey("Hall")]
        public int HallId { get; set; }
        public Hall Hall { get; set; } = null!;

        [ForeignKey("MovieDetail")]
        public int MovieDetailId { get; set; }
        public MovieDetail MovieDetail { get; set; } = null!;
    }
}
