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

namespace MiniCinema.Pages.Department
{
    public class EditModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public EditModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        [BindProperty]
        public Models.Department Department { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.Department == null)
            {
                return NotFound();
            }

            var department =  await _context.Department.FirstOrDefaultAsync(m => m.ID == id);
            if (department == null)
            {
                return NotFound();
            }
            Department = department;
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

            _context.Attach(Department).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!DepartmentExists(Department.ID))
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

        private bool DepartmentExists(int id)
        {
          return (_context.Department?.Any(e => e.ID == id)).GetValueOrDefault();
        }
    }
}
