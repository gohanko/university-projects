using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class TicketSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Ticket == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Ticket.Any())
                {
                    return;   // DB has been seeded
                }

                context.Ticket.AddRange(
                    new Ticket
                    {
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Ticket
    {
        public int Id { get; set; }
        public string BookingNumber { get; set; } = string.Empty;

        [ForeignKey("MovieSession")]
        public int MovieSessionID { get; set; }
        public MovieSession MovieSession { get; set; }
        public ICollection<Transaction> Transactions { get; set; }
    }
}
