#include <vector>
#include <SFML\Graphics.hpp>
#include "../piece/piece.h"
#include "../piece/king/king.h"

class Board {
private:
    sf::Texture m_chess_piece_texture;
    std::vector<sf::RectangleShape> render_objects;
    std::vector<sf::Sprite> render_pieces;
    std::vector<Piece> m_chess_pieces = {
        King(sf::Vector2u(5, 0), 4, 0, 0),
    };
public:
    std::vector<sf::RectangleShape> getRenderObject(sf::Vector2u draw_area, sf::Vector2u amount_of_cells);
    std::vector<sf::Sprite> getRenderPieces();
};