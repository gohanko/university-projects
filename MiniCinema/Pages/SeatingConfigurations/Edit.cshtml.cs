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

namespace MiniCinema.Pages.SeatingConfigurations
{
    public class EditModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public EditModel(MiniCinema.Data.MiniCinemaContext context)
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

            var seatingconfiguration =  await _context.SeatingConfiguration.FirstOrDefaultAsync(m => m.Id == id);
            if (seatingconfiguration == null)
            {
                return NotFound();
            }
            SeatingConfiguration = seatingconfiguration;
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

            _context.Attach(SeatingConfiguration).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!SeatingConfigurationExists(SeatingConfiguration.Id))
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

        private bool SeatingConfigurationExists(int id)
        {
          return (_context.SeatingConfiguration?.Any(e => e.Id == id)).GetValueOrDefault();
        }
    }
}
