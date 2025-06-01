using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.Movies
{
    public class Showtime : PageModel
    {
        private readonly MiniCinemaContext _context;

        public Showtime(MiniCinemaContext context)
        {
            _context = context;
        }

        public Movie Movie { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int movie_id)
        {
            if (_context.Movie == null)
            {
                return NotFound();
            }

            var movie = await _context.Movie
                .Include(movie => movie.Sessions)
                .ThenInclude(sessions => sessions.Hall)
                .ThenInclude(Hall => Hall.Branch)
                .FirstOrDefaultAsync(m => m.MovieId == movie_id);

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
