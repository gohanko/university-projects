using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;

namespace MiniCinema.Pages.Ticketing
{
    public class Booking : PageModel
    {
        private readonly MiniCinemaContext _context;
        public Session Session { get; set; } = default!;
        public ICollection<Seat> RecommendedSeats { get; set; } = default!;
        public Booking(MiniCinemaContext context)
        {
            _context = context;
        }
        public string ConvertNumberToAlphabet(int index)
        {
            const string letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            var value = "";

            if (index >= letters.Length)
                value += letters[index / letters.Length - 1];

            value += letters[index % letters.Length];

            return value;
        }

        public ICollection<Seat> SeatRecommender(ICollection<Seat> ExistingSeats)
        {
            int max_num_of_row = 0;
            int max_num_of_column = 0;

            foreach (var existing_seat in ExistingSeats)
            {
                if (existing_seat.Row > max_num_of_row)
                {
                    max_num_of_row = existing_seat.Row;
                }

                if (existing_seat.Column > max_num_of_column)
                {
                    max_num_of_column = existing_seat.Column;
                }
            }

            int recommended_row_core = (int) ((double) max_num_of_row * ((double) 3/ (double) 4));
            int recommended_column_core = max_num_of_column / 2;

            ICollection<Seat> RecommendedSeats = new List<Seat>();

            foreach (var existing_seat in ExistingSeats)
            {
                if (existing_seat.ProfileId != null)
                {
                    continue;
                }

                Console.WriteLine(existing_seat.Row >= recommended_row_core);

                if (
                    existing_seat.Row >= recommended_row_core
                    && existing_seat.Column >= (recommended_column_core - 1)
                    && existing_seat.Column <= (recommended_column_core + 1)
                )
                {
                    RecommendedSeats.Add(existing_seat);
                }
            }

            return RecommendedSeats;
        }

        public async Task<IActionResult> OnGetAsync(int id)
        {
            if (_context.Session == null)
            {
                return NotFound();
            }

            var session = await _context.Session
                .Include(session => session.Hall).ThenInclude(hall => hall.Seats)
                .FirstOrDefaultAsync(session => session.SessionId == id);

            if (session == null)
            {
                return NotFound();
            }
            else
            {
                Session = session;
                RecommendedSeats = SeatRecommender(Session.Hall.Seats);
            }

            return Page();
        }

        public async Task<Seat> AssignSeat(int profile_id)
        {
            int seat_id = int.Parse(Request.Form["seat_selection"]);
            Seat result = await _context.Seat.FirstOrDefaultAsync(seat => seat.SeatId == seat_id);
            if (result != null)
            {
                result.ProfileId = profile_id;
            }

            return result;
        }

        public async Task<Profile> handleOnProfileFormSubmit()
        {
            var email = Request.Form["email"].ToString();
            var phone_number = Request.Form["phone_number"].ToString();
            var fullname = Request.Form["fullname"].ToString();

            Profile profile = new Profile();

            var query_result = await _context.Profile
                .FirstOrDefaultAsync(profile => profile.Email == email && profile.PhoneNumber == phone_number && profile.Fullname == fullname);

            if (query_result == null)
            {
                Profile profile_new = new Profile();
                profile_new.Fullname = fullname;
                profile_new.Email = email;
                profile_new.PhoneNumber = phone_number;

                _context.Profile.Add(profile_new);
                await _context.SaveChangesAsync();

                profile = profile_new;
            }
            else
            {
                profile = query_result;
            }


            return profile;
        }

        // To protect from overposting attacks, see https://aka.ms/RazorPagesCRUD
        public async Task<IActionResult> OnPostAsync(int id)
        {
            var session = await _context.Session
                .Include(session => session.Hall)
                .ThenInclude(hall => hall.Seats)
                .FirstOrDefaultAsync(session => session.SessionId == id);

            if (session == null)
            {
                return NotFound();
            }

            Session = session;

            if (!ModelState.IsValid)
            {
                return Page();
            }

            Ticket Ticket = new Ticket();

            Profile profile = await handleOnProfileFormSubmit();
            Ticket.SessionId = session.SessionId;
            Ticket.ProfileId = profile.ProfileId;
            Ticket.PriceAmount = 12;
            Seat seat = await AssignSeat(profile.ProfileId);
            if (seat == null)
            {
                return NotFound();
            }

            _context.Ticket.Add(Ticket);
            await _context.SaveChangesAsync();

            return RedirectToPage("/Ticketing/Viewer", new { ticket_id = Ticket.TicketId });
        }
    }
}
