using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.Brs
{
    public class CreateModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public CreateModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        public IActionResult OnGet()
        {
            return Page();
        }

        [BindProperty]
        public Branchs Branchs { get; set; } = default!;
        

        // To protect from overposting attacks, see https://aka.ms/RazorPagesCRUD
        public async Task<IActionResult> OnPostAsync()
        {
          if (!ModelState.IsValid || _context.Branchs == null || Branchs == null)
            {
                return Page();
            }

            _context.Branchs.Add(Branchs);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}
