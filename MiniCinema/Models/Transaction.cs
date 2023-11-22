using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    /*public static class TransactionSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Transaction == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Transaction.Any())
                {
                    return;   // DB has been seeded
                }

                context.Transaction.AddRange(
                    new Transaction
                    {
                    }
                );

                context.SaveChanges();
            }
        }
    }*/
    public class Transaction
    {
        public int TransactionId { get; set; }
        public string PriceCurrency { get; set; } = "MY";

        [DataType(DataType.Currency)]
        [Column(TypeName = "decimal(18, 2)")]
        public decimal PriceAmount { get; set; }

        public string Description { get; set; } = string.Empty;

        [DataType(DataType.DateTime)]
        public DateTime DateTime { get; set; }
        public int TransactionType { get; set; }

        [Column(TypeName = "decimal(18, 2)")]
        public decimal DiscountPercentageApplied { get; set; }

        [ForeignKey("Ticket")]
        public int TicketId { get; set; }
        public Ticket Ticket { get; set; } = null!;
    }
}
