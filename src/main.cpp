#include <SFML\Graphics.hpp>
#include <vector>
#include <SFML\Graphics.hpp>

class Piece {
private:
    sf::RenderWindow * m_window;
    sf::Vector2i m_position;
    bool is_dead = 0;

    // Sprite size is 128x128
    sf::IntRect spritePositionCalculator(int row, int column) {
        const unsigned int sprite_size = 128;
        sf::IntRect spriteRect(column * sprite_size, row * sprite_size, sprite_size, sprite_size);
        return spriteRect;
    };

public:
    Piece(sf::RenderWindow * window, sf::Vector2i position) : m_window(window), m_position(position) {}

    void draw() {
        // 128x128
        sf::Texture texture;
        if (!texture.loadFromFile("./assets/sprites/chess_pieces.png", this->spritePositionCalculator(1, 2))) {
            throw;
        }

        sf::Sprite sprite;
        sprite.setTexture(texture);
        sprite.setPosition(m_position.x, m_position.y);
        m_window->draw(sprite);
    }
};

class Team {
private:
    sf::RenderWindow * m_window;
    enum chess_piece_type { KING, QUEEN, ROOK, KNIGHTS, BISHOPS, PAWNS};
public:
    Team(sf::RenderWindow * window) : m_window(window) {}

    void draw() {

    }
};

class BoardCell {
private:
    sf::RenderWindow * m_window;
    sf::Vector2f m_size;
    sf::Color m_color;
    sf::Vector2i m_position;
public:
    BoardCell(sf::RenderWindow * window, sf::Vector2i position, sf::Vector2f size, sf::Color color)
        : m_window(window), m_position(position), m_size(size), m_color(color) {}

    void draw() {
        sf::RectangleShape cell(m_size);
        cell.setPosition(sf::Vector2f(m_position.x * m_size.x, m_position.y * m_size.y));
        cell.setFillColor(m_color);
        m_window->draw(cell);
    }
};

#define HORIZONTAL_CELL_AMOUNT 8
#define VERTICAL_CELL_AMOUNT 8

class Board {
private:
    sf::RenderWindow * m_window;
    std::vector<BoardCell> m_cells;

    void initialiseBoard() {
        sf::Vector2u currentWindowSize = m_window->getSize();
        sf::Vector2u cellSize(currentWindowSize.x / HORIZONTAL_CELL_AMOUNT, currentWindowSize.y / VERTICAL_CELL_AMOUNT); // Divides the window into cells

        for (int horizontal_counter = 0; horizontal_counter < HORIZONTAL_CELL_AMOUNT; horizontal_counter++) {
            for (int vertical_counter = 0; vertical_counter < VERTICAL_CELL_AMOUNT; vertical_counter++) {    
                BoardCell cell(
                    m_window,
                    sf::Vector2i(horizontal_counter, vertical_counter),
                    sf::Vector2f(cellSize.x, cellSize.y),
                    ((horizontal_counter + vertical_counter) % 2) ? sf::Color {211, 211, 211} : sf::Color::White
                );
                cell.draw();
                m_cells.push_back(cell);
            }
        }
    }

    void initialiseTeams() {

    }
public:
    Board(sf::RenderWindow * window) : m_window(window) {}

    void draw() {
        this->initialiseBoard();
        this->initialiseTeams();
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

        Board main_board(&window);

        window.clear(sf::Color::Black);
        main_board.draw();
        window.display();
    }

    return 0;
};