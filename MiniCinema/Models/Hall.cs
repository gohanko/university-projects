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
                        Type = "",
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Hall
    {
        public int Id { get; set; }
        public string Type { get; set; } = string.Empty;
        [ForeignKey("CinemaBranch")]
        public int CinemaBranchID {  get; set; }
        public Branch CinemaBranch { get; set; }

        [ForeignKey("SeatingConfiguration")]
        public int SeatingConfigurationID { get; set; }
        public SeatingConfiguration SeatingConfiguration { get; set; }
    }
}
