using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.MovieSessions
{
    public class DeleteModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DeleteModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        [BindProperty]
      public MovieSession MovieSession { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.MovieSession == null)
            {
                return NotFound();
            }

            var moviesession = await _context.MovieSession.FirstOrDefaultAsync(m => m.MovieSessionId == id);

            if (moviesession == null)
            {
                return NotFound();
            }
            else 
            {
                MovieSession = moviesession;
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null || _context.MovieSession == null)
            {
                return NotFound();
            }
            var moviesession = await _context.MovieSession.FindAsync(id);

            if (moviesession != null)
            {
                MovieSession = moviesession;
                _context.MovieSession.Remove(MovieSession);
                await _context.SaveChangesAsync();
            }

            return RedirectToPage("./Index");
        }
    }
}
