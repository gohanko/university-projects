#include <SFML\Graphics.hpp>
#include "../../texture_manager/texture_manager.h"

#ifndef PIECE_H
#define PIECE_H

// Base class for Chess Pieces, do not use directly
class Piece {
private:
    sf::Vector2u m_position;
    sf::Texture m_texture;
    TextureManager texture_manager;
    sf::Sprite m_sprite;
    int m_team;
public:
    Piece(sf::Vector2u position, int sprite_x, int sprite_y, int team);
    bool isMoveValid(sf::Vector2u new_position);
    void setPosition(sf::Vector2u new_position);
    void setSprite(int x, int y);
    sf::Sprite getSprite() const;
    bool move(sf::Vector2u new_position);
};

#endif