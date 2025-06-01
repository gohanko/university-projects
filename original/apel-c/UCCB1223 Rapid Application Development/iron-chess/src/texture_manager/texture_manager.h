#include <map>
#include <SFML/Graphics.hpp>

#ifndef TEXTURE_MANAGER_H
#define TEXTURE_MANAGER_H


class TextureManager {
    private:
        std::map<std::string, sf::Texture> textures;

    public:
        void add_texture(std::string texture_name, sf::Texture texture);
        sf::Texture* get_texture(std::string texture_name);
};

#endif