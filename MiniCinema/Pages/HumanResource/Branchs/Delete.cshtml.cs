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
    public class DeleteModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DeleteModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        [BindProperty]
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

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null || _context.Branchs == null)
            {
                return NotFound();
            }
            var branchs = await _context.Branchs.FindAsync(id);

            if (branchs != null)
            {
                Branchs = branchs;
                _context.Branchs.Remove(Branchs);
                await _context.SaveChangesAsync();
            }

            return RedirectToPage("./Index");
        }
    }
}
