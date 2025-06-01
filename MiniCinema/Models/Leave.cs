using System.ComponentModel.DataAnnotations;

namespace MiniCinema.Models
{
    public class Leave
    {
        public int ID { get; set; }
        public string Name { get; set; } = string.Empty;

        public string Department { get; set; } = string.Empty;
        public string Position { get; set; } = string.Empty;

        [DataType(DataType.Date)]
        public DateTime LeaveStartDate { get; set; }
        public DateTime LeaveEndDate { get; set; }
        public string ReasonForLeave { get; set; } = string.Empty;
       
   
    }
}
