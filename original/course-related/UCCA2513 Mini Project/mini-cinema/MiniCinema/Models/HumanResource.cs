using System.ComponentModel.DataAnnotations;

namespace MiniCinema.Models
{
    public class HumanResource

    {
        public int ID { get; set; }
        public string Name { get; set; } = string.Empty;
        public int Age { get; set; }

        [DataType(DataType.Date)]
        public DateTime BirthdayDate { get; set; }
        public string Position { get; set; } = string.Empty;
        public string Department { get; set; } = string.Empty;
        public decimal Salary { get; set; }
    }
}
