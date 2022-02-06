#include "base.h"

// Replace this in the inherited class.
bool Base::isMoveValid(sf::Vector2u new_position) {
    return true;
}

void Base::setPosition(sf::Vector2u new_position) {
    this->position = new_position;
}

bool Base::move(sf::Vector2u new_position) {
    if (!this->isMoveValid(new_position)) {
        return false;
    }

    this->setPosition(new_position);
    return true;
}