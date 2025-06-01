#include "piece.h"
#include "team.h"

Piece::Piece(sf::Vector2u position, int sprite_x, int sprite_y, int team) {
    this->setPosition(position);
    this->setSprite(sprite_x, sprite_y);
    team = m_team;
}

// Replace this in the inherited class.
bool Piece::isMoveValid(sf::Vector2u new_position) {
    return true;
}

void Piece::setPosition(sf::Vector2u new_position) {
    this->m_position = new_position;
}

void Piece::setSprite(int x, int y) {
    if (!this->m_texture.loadFromFile("./resources/sprites/chess_pieces.png")) {
        return;
    }

    texture_manager.add_texture("chess_pieces", m_texture);

    this->m_sprite.setTexture(*texture_manager.get_texture("chess_pieces"));
    this->m_sprite.setTextureRect(sf::IntRect(128 * x, 128 * y, 128, 128));
}

sf::Sprite Piece::getSprite() const {
    return this->m_sprite;
}

bool Piece::move(sf::Vector2u new_position) {
    if (!this->isMoveValid(new_position)) {
        return false;
    }

    this->setPosition(new_position);
    return true;
}