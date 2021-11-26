#include <iostream>
#include <string>
#include <SFML\Graphics.hpp>

#define DEFAULT_WINDOW_WIDTH 800
#define DEFAULT_WINDOW_HEIGHT 600
class Piece {
    unsigned int position_x;
    unsigned int position_y;
};

class Castle: public Piece {

};

class Teams {

};

class Board {
    
};

class Text {
private:
    sf::RenderWindow * m_window;
    std::string m_text;
    sf::Vector2f m_coordinates;
    sf::FloatRect textRect;
public:
    Text(sf::RenderWindow * window, std::string text, sf::Vector2f coordinates)
        : m_window(window), m_text(text), m_coordinates(coordinates) {}

    bool onHover() {
        sf::Vector2f mousePosition = m_window->mapPixelToCoords(sf::Mouse::getPosition(*m_window));
        return mousePosition.x > (m_coordinates.x - (textRect.width / 2.0f)) && mousePosition.x < (m_coordinates.x + (textRect.width / 2.0f))
            && mousePosition.y > (m_coordinates.y - (textRect.height / 2.0f)) && mousePosition.y < (m_coordinates.y + (textRect.height / 2.0f));
    }

    bool onClick() {
        return this->onHover() && sf::Mouse::isButtonPressed(sf::Mouse::Left);
    }

    void draw(bool onClickClose = false, bool onHoverHighlightText = false) {
        sf::Font font;
        if (!font.loadFromFile("assets/fonts/Roboto/Roboto-Regular.ttf")) {
            throw; // TODO: Return an error stating no fonts found here.
        }

        sf::Text text;
        text.setFont(font);
        text.setString(m_text);

        // Centering the origin
        textRect = text.getLocalBounds();
        text.setOrigin(textRect.left + textRect.width / 2.0f, textRect.top + textRect.height / 2.0f);
        text.setPosition(m_coordinates.x, m_coordinates.y);
        if (this->onClick()) {
            if (onClickClose) {
                m_window->close();
            }
        }

        if (this->onHover()) {
            if (onHoverHighlightText) {
                text.setColor(sf::Color::Red);
            }
        }

        m_window->draw(text);
    }
};

class Menu {
private:
    sf::RenderWindow * m_window;
public:
    Menu(sf::RenderWindow * window): m_window(window) {}

    void draw() {
        sf::Vector2u windowSize = m_window->getSize();
        sf::Vector2u cellSize(windowSize.x / 8, windowSize.y / 20); // Divides the window into cells

        Text title(m_window, "Moe Chess!", sf::Vector2f(cellSize.x * 4, cellSize.y * 5));
        title.draw();
        
        Text start(m_window, "Start Game", sf::Vector2f(cellSize.x * 4, cellSize.y * 10));
        start.draw(false, true);

        Text quit(m_window, "Quit", sf::Vector2f(cellSize.x * 4, cellSize.y * 12));
        quit.draw(true, true);
    }
};

int main() {
    sf::RenderWindow window(sf::VideoMode(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT), "Moe Chess!", sf::Style::Default);

    while (window.isOpen()) {

        sf::Event event;
        while (window.pollEvent(event)) 
        {
            if (event.type == sf::Event::Closed)
                window.close();
        }
        Menu menu(&window);

        window.clear(sf::Color::Black);
        menu.draw();
        window.display();
    }

    return 0;
};