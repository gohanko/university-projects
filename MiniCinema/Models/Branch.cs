using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using System.ComponentModel.DataAnnotations.Schema;

namespace MiniCinema.Models
{
    public static class BranchSeedData
    {
        public static void Initialize(IServiceProvider serviceProvider)
        {
            using (var context = new MiniCinemaContext(serviceProvider.GetRequiredService<DbContextOptions<MiniCinemaContext>>()))
            {
                if (context == null || context.Branch == null)
                {
                    throw new ArgumentNullException("Null MiniCinemaContext");
                }

                // Look for any movies.
                if (context.Branch.Any())
                {
                    return;   // DB has been seeded
                }

                context.Branch.AddRange(
                    new Branch
                    {
                        Name = "MiniCinema Kampar",
                        AddressId = 1
                    }
                );

                context.SaveChanges();
            }
        }
    }
    public class Branch
    {
        public int BranchId { get; set; }
        public string Name { get; set; } = string.Empty;

        [ForeignKey("AddressAddress")]
        public int AddressId { get; set; }
        public Address Address { get; set; } = null!;
    }
}
