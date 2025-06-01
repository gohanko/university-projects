using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.Brs
{
    public class DetailsModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DetailsModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

      public Branchs Branchs { get; set; } = default!; 

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.Branchs == null)
            {
                return NotFound();
            }

            var branchs = await _context.Branchs.FirstOrDefaultAsync(m => m.ID == id);
            if (branchs == null)
            {
                return NotFound();
            }
            else 
            {
                Branchs = branchs;
            }
            return Page();
        }
    }
}
