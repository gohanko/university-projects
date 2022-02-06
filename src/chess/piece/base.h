#include <SFML\Graphics.hpp>

// Base class for Chess Pieces, do not use directly
class Base {
private:
    sf::Vector2u position;

public:
    bool isMoveValid(sf::Vector2u new_position);
    void setPosition(sf::Vector2u new_position);
    bool move(sf::Vector2u new_position);
};