#include <SFML\Graphics.hpp>
#include <vector>

class Renderer {
    private:
        sf::RenderWindow * m_window;
        std::vector<sf::RectangleShape> m_render_objects;
        std::vector<sf::Sprite> m_sprite_objects;
    public:
        Renderer(sf::RenderWindow * window);
        void addRenderObjects(std::vector<sf::RectangleShape> render_objects);
        void addSpriteObjects(std::vector<sf::Sprite> sprite_objects); 
        void render();
};