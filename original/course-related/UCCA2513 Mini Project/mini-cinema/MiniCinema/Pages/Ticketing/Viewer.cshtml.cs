using Microsoft.AspNetCore.Http.Extensions;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using MiniCinema.Data;
using MiniCinema.Models;
using QRCoder;
using System;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;

namespace MiniCinema.Pages.Ticketing
{
    public class Viewer : PageModel
    {
        private readonly MiniCinemaContext _context;

        public Viewer(MiniCinemaContext context)
        {
            _context = context;
        }

        public Ticket Ticket { get; set; } = default!;
        public string Base64QRUri { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int ticket_id)
        {
            if (_context.Ticket == null)
            {
                return NotFound();
            }

            var ticket = await _context.Ticket
                .Include(ticket => ticket.Session).ThenInclude(session => session.Movie)
                .Include(ticket => ticket.Session).ThenInclude(session => session.Hall).ThenInclude(hall => hall.Branch)
                .FirstOrDefaultAsync(m => m.TicketId == ticket_id);

            if (ticket == null)
            {
                return NotFound();
            }
            else
            {
                Base64QRUri = GenerateQRCodeInBase64(Request.GetEncodedUrl());
                Ticket = ticket;
            }
            return Page();
        }

        public string GenerateQRCodeInBase64(string input)
        {
            QRCodeGenerator QrGenerator = new QRCodeGenerator();
            QRCodeData QrCodeInfo = QrGenerator.CreateQrCode(input, QRCodeGenerator.ECCLevel.Q);
            Base64QRCode qrCode = new Base64QRCode(QrCodeInfo);
            string QrCodeAsBase64 = qrCode.GetGraphic(20);
            return string.Format("data:image/png;base64,{0}", QrCodeAsBase64);
        }
    }
}
