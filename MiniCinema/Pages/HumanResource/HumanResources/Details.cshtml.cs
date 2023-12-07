using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.HumanResources
{
    public class DetailsModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public DetailsModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

      public Models.HumanResource HumanResource { get; set; } = default!; 

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null || _context.HumanResource == null)
            {
                return NotFound();
            }

            var human_resource = await _context.HumanResource.FirstOrDefaultAsync(m => m.ID == id);
            if (human_resource == null)
            {
                return NotFound();
            }
            else 
            {
                HumanResource = human_resource;
            }
            return Page();
        }
    }
}
