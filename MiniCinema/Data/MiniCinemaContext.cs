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

        // Ticketing
        public DbSet<MiniCinema.Models.Branch> Branch { get; set; } = default!;
        public DbSet<MiniCinema.Models.Hall> Hall { get; set; } = default!;
        public DbSet<MiniCinema.Models.Address> Address { get; set; } = default!;
        public DbSet<MiniCinema.Models.Movie> Movie { get; set; } = default!;
        public DbSet<MiniCinema.Models.Profile> Profile { get; set; } = default!;
        public DbSet<MiniCinema.Models.Session> Session { get; set; } = default!;
        public DbSet<MiniCinema.Models.Seat> Seat { get; set; } = default!;
        public DbSet<MiniCinema.Models.Ticket> Ticket { get; set; } = default!;

        // Human Resource
        public DbSet<MiniCinema.Models.HumanResource>? HumanResource { get; set; }
        public DbSet<MiniCinema.Models.Department>? Department { get; set; }
        public DbSet<MiniCinema.Models.Branchs>? Branchs { get; set; }
        public DbSet<MiniCinema.Models.Resign>? Resign { get; set; }
        public DbSet<MiniCinema.Models.Leave>? Leave { get; set; }
    }
}
