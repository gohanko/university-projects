using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class MovieSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Movie == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Movie.Any())
                {
                    return;   // DB has been seeded
                }

                context.Movie.AddRange(
                    new Movie
                    {
                        Title = "Castle in the Sky",
                        ReleaseDate = DateTime.Parse("1986-8-2"),
                        Language = "JP",
                        RuntimeMinutes = 125,
                        ImageURL = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/nx513-G47zqEhndFBY.jpg",
                        Genre = "Anime",
                        Rating = "R",
                    },
                    new Movie
                    {
                        Title = "Tales from Earthsea",
                        ReleaseDate = DateTime.Parse("2006-7-29"),
                        Language = "JP",
                        RuntimeMinutes = 115,
                        ImageURL = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/large/bx1829-DqhBRfDU7n6D.jpg",
                        Genre = "Anime",
                        Rating = "R",
                    },
                    new Movie
                    {
                        Title = "Ghostbusters 2",
                        ReleaseDate = DateTime.Parse("1986-2-23"),
                        Language = "EN",
                        RuntimeMinutes = 120,
                        ImageURL = "https://m.media-amazon.com/images/M/MV5BNWI5NTg3MTMtZWY0Ny00YWJmLThkZDUtMTBkZTVhMDAyMmVlXkEyXkFqcGdeQXVyNjc5NjEzNA@@._V1_.jpg",
                        Genre = "Comedy",
                        Rating = "R",
                    },

                    new Movie
                    {
                        Title = "时光代理人",
                        ReleaseDate = DateTime.Parse("2021-4-30"),
                        Language = "CN",
                        RuntimeMinutes = 190,
                        ImageURL = "https://s4.anilist.co/file/anilistcdn/media/anime/cover/medium/bx136484-nBYX9HCItKjb.jpg",
                        Genre = "Donghua",
                        Rating = "R",
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Movie
    {
        [Display(Name = "Movie ID")]
        public int MovieId { get; set; }

        [StringLength(60, MinimumLength = 3)]
        [Required]
        public string Title { get; set; } = string.Empty;

        [Display(Name = "Release Date")]
        [DataType(DataType.DateTime)]
        public DateTime ReleaseDate { get; set; }
        public string Language { get; set; } = string.Empty;
        public int RuntimeMinutes { get; set; } = 0;

        public string ImageURL { get; set; } = string.Empty;

        [RegularExpression(@"^[A-Z]+[a-zA-Z\s]*$")]
        [Required]
        [StringLength(30)]
        public string Genre { get; set; } = string.Empty;

        [RegularExpression(@"^[A-Z]+[a-zA-Z0-9""'\s-]*$")]
        [StringLength(5)]
        [Required]
        public string Rating { get; set; } = string.Empty;

        public ICollection<Session> Sessions { get; set; } = null!;
    }
}
