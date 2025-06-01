namespace MiniCinema.Models
{
    public class Branchs
    {
        public int ID { get; set; }
        public string BranchName { get; set; } = string.Empty;

        public string Area { get; set; } = string.Empty;
        public string BranchManager { get; set; } = string.Empty;
        public int Postcode { get; set; }
    }
}
