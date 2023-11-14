using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class MovieDetailSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.MovieDetail == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.MovieDetail.Any())
                {
                    return;   // DB has been seeded
                }

                context.MovieDetail.AddRange(
                    new MovieDetail
                    {
                        Title = "Castle in the Sky",
                        ReleaseDate = DateTime.Parse("1986-8-2"),
                        Language = "JP",
                        RuntimeMinutes = 125,
                        Genre = "Anime",
                        Rating = "R",
                    },
                    new MovieDetail
                    {
                        Title = "Tales from Earthsea",
                        ReleaseDate = DateTime.Parse("2006-7-29"),
                        Language = "JP",
                        RuntimeMinutes = 115,
                        Genre = "Anime",
                        Rating = "R",
                    },
                    new MovieDetail
                    {
                        Title = "Ghostbusters 2",
                        ReleaseDate = DateTime.Parse("1986-2-23"),
                        Language = "EN",
                        RuntimeMinutes = 120,
                        Genre = "Comedy",
                        Rating = "R",
                    },

                    new MovieDetail
                    {
                        Title = "时光代理人",
                        ReleaseDate = DateTime.Parse("2021-4-30"),
                        Language = "CN",
                        RuntimeMinutes = 190,
                        Genre = "Donghua",
                        Rating = "R",
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class MovieDetail
    {
        public int MovieDetailId { get; set; }

        [StringLength(60, MinimumLength = 3)]
        [Required]
        public string Title { get; set; } = string.Empty;

        [Display(Name = "Release Date")]
        [DataType(DataType.DateTime)]
        public DateTime ReleaseDate { get; set; }
        public string Language { get; set; } = string.Empty;
        public int RuntimeMinutes { get; set; } = 0;

        [RegularExpression(@"^[A-Z]+[a-zA-Z\s]*$")]
        [Required]
        [StringLength(30)]
        public string Genre { get; set; } = string.Empty;

        [RegularExpression(@"^[A-Z]+[a-zA-Z0-9""'\s-]*$")]
        [StringLength(5)]
        [Required]
        public string Rating { get; set; } = string.Empty;

        [ForeignKey("MovieSession")]
        public ICollection<MovieSession> MovieSessions { get; set; } = null!;
    }
}
