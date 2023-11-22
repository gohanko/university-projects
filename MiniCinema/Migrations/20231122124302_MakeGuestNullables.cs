using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MiniCinema.Migrations
{
    public partial class MakeGuestNullables : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Seat_Guest_GuestId",
                table: "Seat");

            migrationBuilder.AlterColumn<int>(
                name: "GuestId",
                table: "Seat",
                type: "int",
                nullable: true,
                oldClrType: typeof(int),
                oldType: "int");

            migrationBuilder.AddForeignKey(
                name: "FK_Seat_Guest_GuestId",
                table: "Seat",
                column: "GuestId",
                principalTable: "Guest",
                principalColumn: "GuestId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Seat_Guest_GuestId",
                table: "Seat");

            migrationBuilder.AlterColumn<int>(
                name: "GuestId",
                table: "Seat",
                type: "int",
                nullable: false,
                defaultValue: 0,
                oldClrType: typeof(int),
                oldType: "int",
                oldNullable: true);

            migrationBuilder.AddForeignKey(
                name: "FK_Seat_Guest_GuestId",
                table: "Seat",
                column: "GuestId",
                principalTable: "Guest",
                principalColumn: "GuestId",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
