using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages
{
    public class ShowtimeByMovie : PageModel
    {
        private readonly MiniCinemaContext _context;

        public ShowtimeByMovie(MiniCinemaContext context)
        {
            _context = context;
        }

        public Movie Movie { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.Movie == null)
            {
                return NotFound();
            }

            var movie = await _context.Movie
                .Include(movie => movie.Sessions)
                .ThenInclude(sessions => sessions.Hall)
                .ThenInclude(Hall => Hall.Branch)
                .FirstOrDefaultAsync(m => m.MovieId == id);

            if (movie == null)
            {
                return NotFound();
            }
            else
            {
                Movie = movie;
            }
            return Page();
        }
    }
}
