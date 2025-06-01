using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.Department
{
    public class IndexModel : PageModel
    {
        private readonly MiniCinema.Data.MiniCinemaContext _context;

        public IndexModel(MiniCinema.Data.MiniCinemaContext context)
        {
            _context = context;
        }

        public IList<Models.Department> Department { get;set; } = default!;

        public async Task OnGetAsync()
        {
            if (_context.Department != null)
            {
                Department = await _context.Department.ToListAsync();
            }
        }
    }
}
