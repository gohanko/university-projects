#include "texture_manager.h"

void TextureManager::add_texture(std::string texture_name, sf::Texture texture) {
    textures.insert({texture_name, texture});
}

sf::Texture* TextureManager::get_texture(std::string texture_name) {
    return &textures.at(texture_name);
}
