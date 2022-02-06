#include <vector>
#include <SFML\Graphics.hpp>

class Board {
private:
    sf::Texture m_chess_piece_texture;
    std::vector<sf::RectangleShape> render_objects;
public:
    std::vector<sf::RectangleShape> getRenderObject(sf::Vector2u draw_area, sf::Vector2u amount_of_cells);
};