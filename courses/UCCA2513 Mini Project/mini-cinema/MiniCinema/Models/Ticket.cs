using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
   /* public static class TicketSeedData
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
    }*/
    public class Ticket
    {
        public int TicketId { get; set; }

        [DataType(DataType.Currency)]
        [Column(TypeName = "decimal(18, 2)")]
        public decimal PriceAmount { get; set; }

        [ForeignKey("Profile")]
        public int ProfileId { get; set; }
        public Profile Profile { get; set; } = null!;

        [ForeignKey("Session")]
        public int SessionId { get; set; }
        public Session Session { get; set; } = null!;
    }
}
