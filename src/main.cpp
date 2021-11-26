#include <SFML\Window.hpp>

class Board {

};

class Piece {


};

class MenuGUI {

};

int main() {
    sf::Window window(sf::VideoMode(800, 600), "Moe Chess!");

    while (window.isOpen()) {

        sf::Event event;
        while (window.pollEvent(event)) {
            if (event.type == sf::Event::Closed) {
                window.close();
            }
        }
    }

    return 0;
};