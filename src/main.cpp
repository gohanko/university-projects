#include <SFML\Graphics.hpp>

int main() {
    sf::RenderWindow window(sf::VideoMode(800, 600), "Moe Chess!");

    while (window.isOpen()) {

        sf::Event event;
        while (window.pollEvent(event)) 
        {
            if (event.type == sf::Event::Closed)
                window.close();
        }


        // set the shape color to green
        sf::CircleShape shape(50.f);
        shape.setFillColor(sf::Color(100, 250, 50));

        window.clear(sf::Color::Red);
        window.draw(shape);
        window.display();
    }

    return 0;
};