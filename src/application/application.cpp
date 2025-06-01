#include <vector>
#include <SFML\Graphics.hpp>
#include "../renderer/renderer.h"
#include "../chess/board/board.h"

enum SCREEN: int {
    MENU,
    GAME,
    SETTING,
};

int main() {
    sf::RenderWindow window(sf::VideoMode(800, 800), "Moe Chess!", sf::Style::Close);
    Renderer renderer(&window);
    Board board;
    
    // States
    int current_screen = SCREEN::GAME;

    while (window.isOpen()) {
        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed)
                window.close();
        }

        // Initializing screens
        switch (current_screen) {
            case SCREEN::MENU:
                renderer.render();
                break;
            case SCREEN::GAME:
                renderer.addRenderObjects(board.getRenderObject(window.getSize(), sf::Vector2u(8, 8)));
                renderer.addSpriteObjects(board.getRenderPieces());
                renderer.render();
                break;
            case SCREEN::SETTING:
                break;
            default:
                break;
        }
    }

    return 0;
};
