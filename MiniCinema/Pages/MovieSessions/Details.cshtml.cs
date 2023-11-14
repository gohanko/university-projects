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
    public class DetailsModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DetailsModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

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
    }
}
