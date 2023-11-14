using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace MiniCinema.Migrations
{
    /// <inheritdoc />
    public partial class NewMovieSessionIcollection : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "MovieSession",
                table: "MovieSession",
                type: "int",
                nullable: true);

            migrationBuilder.CreateIndex(
                name: "IX_MovieSession_MovieSession",
                table: "MovieSession",
                column: "MovieSession");

            migrationBuilder.AddForeignKey(
                name: "FK_MovieSession_MovieDetail_MovieSession",
                table: "MovieSession",
                column: "MovieSession",
                principalTable: "MovieDetail",
                principalColumn: "MovieDetailId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_MovieSession_MovieDetail_MovieSession",
                table: "MovieSession");

            migrationBuilder.DropIndex(
                name: "IX_MovieSession_MovieSession",
                table: "MovieSession");

            migrationBuilder.DropColumn(
                name: "MovieSession",
                table: "MovieSession");
        }
    }
}
