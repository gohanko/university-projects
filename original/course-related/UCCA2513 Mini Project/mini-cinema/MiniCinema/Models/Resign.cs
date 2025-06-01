using System.ComponentModel.DataAnnotations;

namespace MiniCinema.Models
{
    public class Resign
    {
        public int ID { get; set; }
        public string Name { get; set; } = string.Empty;
        public int Age { get; set; }

        [DataType(DataType.Date)]
        public DateTime ResignDate { get; set; }
        public string Position { get; set; } = string.Empty;
        public string Department { get; set; } = string.Empty;
        public decimal LastSalary { get; set; }
    }
}
