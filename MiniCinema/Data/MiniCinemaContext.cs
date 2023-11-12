using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Models;

namespace MiniCinema.Data
{
    public class MiniCinemaContext : DbContext
    {
        public MiniCinemaContext (DbContextOptions<MiniCinemaContext> options)
            : base(options)
        {
        }

        public DbSet<MiniCinema.Models.Branch> Branch { get; set; } = default!;
        public DbSet<MiniCinema.Models.Guest> Guest { get; set; } = default!;
        public DbSet<MiniCinema.Models.Hall> Hall { get; set; } = default!;
        public DbSet<MiniCinema.Models.LocationAddress> LocationAddress { get; set; } = default!;
        public DbSet<MiniCinema.Models.MovieDetail> MovieDetail { get; set; } = default!;
        public DbSet<MiniCinema.Models.MovieSession> MovieSession { get; set; } = default!;
        public DbSet<MiniCinema.Models.Seat> Seat { get; set; } = default!;
        public DbSet<MiniCinema.Models.SeatingConfiguration> SeatingConfiguration { get; set; } = default!;
        public DbSet<MiniCinema.Models.SeatType> SeatType { get; set; } = default!;
        public DbSet<MiniCinema.Models.Ticket> Ticket { get; set; } = default!;
        public DbSet<MiniCinema.Models.Transaction> Transaction { get; set; } = default!;
        public DbSet<MiniCinema.Models.TransactionType> TransactionType { get; set; } = default!;
    }
}
