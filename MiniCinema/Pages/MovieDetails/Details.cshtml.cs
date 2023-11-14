using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.MovieDetails
{
    public class DetailsModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DetailsModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

      public MovieDetail MovieDetail { get; set; } = default!; 

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.MovieDetail == null)
            {
                return NotFound();
            }

            var moviedetail = await _context.MovieDetail.FirstOrDefaultAsync(m => m.MovieDetailId == id);
            if (moviedetail == null)
            {
                return NotFound();
            }
            else 
            {
                MovieDetail = moviedetail;
            }
            return Page();
        }
    }
}
