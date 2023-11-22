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