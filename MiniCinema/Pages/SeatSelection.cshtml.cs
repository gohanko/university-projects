using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages
{
    public class SeatingByHall : PageModel
    {
        private readonly MiniCinemaContext _context;

        public SeatingByHall(MiniCinemaContext context)
        {
            _context = context;
        }

        public Hall Hall { get; set; } = default!;

        public void OnGet(int? id)
        {

        }
    }
}
