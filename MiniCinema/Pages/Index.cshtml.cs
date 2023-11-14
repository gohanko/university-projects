using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Models;

namespace MiniCinema.Pages
{
    public class IndexModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public IndexModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        public IList<MovieDetail> MovieDetails { get; set; } = default!;
        public async Task OnGetAsync()
        {
            if (_context.MovieDetail != null)
            {
                MovieDetails = await _context.MovieDetail
                    .Include(m => m.MovieSessions)
                    .ToListAsync();
            }
        }
    }
}