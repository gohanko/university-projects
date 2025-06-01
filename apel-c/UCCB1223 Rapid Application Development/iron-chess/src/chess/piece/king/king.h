#include "../piece.h"

class King: public Piece {
    using Piece::Piece;
    bool isMoveValid(sf::Vector2u new_position);
};