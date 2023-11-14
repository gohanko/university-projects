using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.MovieSessions
{
    public class EditModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public EditModel(MiniCinema.Data.MiniCinemaContext context)
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

            var moviesession =  await _context.MovieSession.FirstOrDefaultAsync(m => m.MovieSessionId == id);
            if (moviesession == null)
            {
                return NotFound();
            }
            MovieSession = moviesession;
           ViewData["HallId"] = new SelectList(_context.Hall, "HallId", "HallId");
           ViewData["MovieDetailId"] = new SelectList(_context.MovieDetail, "MovieDetailId", "Genre");
            return Page();
        }

        // To protect from overposting attacks, enable the specific properties you want to bind to.
        // For more details, see https://aka.ms/RazorPagesCRUD.
        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            _context.Attach(MovieSession).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!MovieSessionExists(MovieSession.MovieSessionId))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return RedirectToPage("./Index");
        }

        private bool MovieSessionExists(int id)
        {
          return (_context.MovieSession?.Any(e => e.MovieSessionId == id)).GetValueOrDefault();
        }
    }
}
