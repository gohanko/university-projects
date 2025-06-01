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

namespace MiniCinema.Pages.Brs
{
    public class EditModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public EditModel(MiniCinema.Data.MiniCinemaContext context)
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

            var branchs =  await _context.Branchs.FirstOrDefaultAsync(m => m.ID == id);
            if (branchs == null)
            {
                return NotFound();
            }
            Branchs = branchs;
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

            _context.Attach(Branchs).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!BranchsExists(Branchs.ID))
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

        private bool BranchsExists(int id)
        {
          return (_context.Branchs?.Any(e => e.ID == id)).GetValueOrDefault();
        }
    }
}
