using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;

namespace MiniCinema.Models
{
   /* public static class TransactionTypeSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.TransactionType == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.TransactionType.Any())
                {
                    return;   // DB has been seeded
                }

                context.TransactionType.AddRange(
                    new TransactionType
                    {
                    }
                );

                context.SaveChanges();
            }
        }
    }*/
    public class TransactionType
    {
        public int TransactionTypeId { get; set; }
        public string Name { get; set; } = string.Empty;
    }
}
