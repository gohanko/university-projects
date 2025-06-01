using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Models;

namespace MiniCinema.Pages.Movies
{
    public class Listing : PageModel
    {
        private readonly Data.MiniCinemaContext _context;

        public Listing(Data.MiniCinemaContext context)
        {
            _context = context;
        }

        public IList<Movie> Movies { get; set; } = default!;
        public async Task OnGetAsync()
        {
            if (_context.Movie != null)
            {
                Movies = await _context.Movie
                    .Include(m => m.Sessions)
                    .ToListAsync();
            }
        }
    }
}