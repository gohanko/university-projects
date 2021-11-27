#include <vector>
#include <SFML\Graphics.hpp>

class Piece {
private:
    sf::RenderWindow * m_window;
    sf::Texture m_texture;
    sf::Vector2i m_position;
    bool is_dead = 0;

    // Sprite size is 128x128
    sf::IntRect _calculateSprintRect(int row, int column) {
        const unsigned int sprite_size = 128;
        sf::IntRect spriteRect(column * sprite_size, row * sprite_size, sprite_size, sprite_size);
        return spriteRect;
    };
public:
    Piece(sf::RenderWindow * window, sf::Texture texture, sf::Vector2i position) : m_window(window), m_texture(texture), m_position(position) {}

    void draw() {
        sf::Sprite sprite;
        sprite.setTexture(m_texture);
        sprite.setPosition(m_position.x, m_position.y);
        sprite.setTextureRect(_calculateSprintRect(0, 1));
        m_window->draw(sprite);
    }
};

class PieceSet {
private:
    std::vector<Piece> pieces;

    bool _initialiseSet() {
        
    }
public:

};

class Game {
private:
    sf::RenderWindow * m_window;
    sf::Texture m_chess_piece_texture;

    void _drawGameBoard(sf::Vector2u draw_area, sf::Vector2u amount_of_cells) {
        sf::Vector2f cell_size(draw_area.x / amount_of_cells.x, draw_area.y / amount_of_cells.y);

        for (unsigned short horizontal_position=0; horizontal_position < amount_of_cells.x; horizontal_position++) {
            for (unsigned short vertical_position=0; vertical_position < amount_of_cells.y; vertical_position++) {
                sf::RectangleShape cell(cell_size);
                cell.setPosition(sf::Vector2f(horizontal_position * cell_size.x, vertical_position * cell_size.y));
                cell.setFillColor(((horizontal_position + vertical_position) % 2) ? sf::Color {211, 211, 211} : sf::Color::White);
                m_window->draw(cell);
            }
        }
    }
public:
    Game(sf::RenderWindow * window) : m_window(window) {
        if (!m_chess_piece_texture.loadFromFile("./assets/sprites/chess_pieces.png")) {
            throw;
        }
    }

    void render() {
        this->_drawGameBoard(this->m_window->getSize(), sf::Vector2u(8, 8));
    }
};

int main() {
    sf::RenderWindow window(sf::VideoMode(800, 800), "Moe Chess!", sf::Style::Close);

    while (window.isOpen()) {

        sf::Event event;
        while (window.pollEvent(event)) 
        {
            if (event.type == sf::Event::Closed)
                window.close();
        }

        Game main_game(&window);

        window.clear(sf::Color::Black);
        main_game.render();
        window.display();
    }

    return 0;
};
