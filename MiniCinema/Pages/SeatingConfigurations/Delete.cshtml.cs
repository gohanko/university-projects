using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.SeatingConfigurations
{
    public class DeleteModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DeleteModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        [BindProperty]
      public SeatingConfiguration SeatingConfiguration { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.SeatingConfiguration == null)
            {
                return NotFound();
            }

            var seatingconfiguration = await _context.SeatingConfiguration.FirstOrDefaultAsync(m => m.Id == id);

            if (seatingconfiguration == null)
            {
                return NotFound();
            }
            else 
            {
                SeatingConfiguration = seatingconfiguration;
            }
            return Page();
        }

        public async Task<IActionResult> OnPostAsync(int? id)
        {
            if (id == null || _context.SeatingConfiguration == null)
            {
                return NotFound();
            }
            var seatingconfiguration = await _context.SeatingConfiguration.FindAsync(id);

            if (seatingconfiguration != null)
            {
                SeatingConfiguration = seatingconfiguration;
                _context.SeatingConfiguration.Remove(SeatingConfiguration);
                await _context.SaveChangesAsync();
            }

            return RedirectToPage("./Index");
        }
    }
}
