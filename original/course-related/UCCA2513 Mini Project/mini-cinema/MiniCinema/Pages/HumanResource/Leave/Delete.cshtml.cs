using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.Lea
{
    public class DeleteModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DeleteModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        [BindProperty]
      public Leave Leave { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.Leave == null)
            {
                return NotFound();
            }

            var leave = await _context.Leave.FirstOrDefaultAsync(m => m.ID == id);

            if (leave == null)
            {
                return NotFound();
            }
            else 
            {
                Leave = leave;
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null || _context.Leave == null)
            {
                return NotFound();
            }
            var leave = await _context.Leave.FindAsync(id);

            if (leave != null)
            {
                Leave = leave;
                _context.Leave.Remove(Leave);
                await _context.SaveChangesAsync();
            }

            return RedirectToPage("./Index");
        }
    }
}
