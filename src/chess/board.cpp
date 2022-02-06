#include <vector>
#include <SFML\Graphics.hpp>

class Board {
private:
    sf::Texture m_chess_piece_texture;
    std::vector<sf::RectangleShape> render_objects;
public:
    std::vector<sf::RectangleShape> getRenderObject(sf::Vector2u draw_area, sf::Vector2u amount_of_cells) {
        sf::Vector2f cell_size(draw_area.x / amount_of_cells.x, draw_area.y / amount_of_cells.y);

        for (unsigned short horizontal_position=0; horizontal_position < amount_of_cells.x; horizontal_position++) {
            for (unsigned short vertical_position=0; vertical_position < amount_of_cells.y; vertical_position++) {
                sf::RectangleShape cell(cell_size);
                cell.setPosition(sf::Vector2f(horizontal_position * cell_size.x, vertical_position * cell_size.y));
                cell.setFillColor(((horizontal_position + vertical_position) % 2) ? sf::Color {211, 211, 211} : sf::Color::White);
                render_objects.push_back(cell);
            };
        }

        return render_objects;
    }
};