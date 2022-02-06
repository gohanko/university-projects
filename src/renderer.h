#include <SFML\Graphics.hpp>

class Renderer {
    public:
        Renderer(sf::RenderWindow * window) {}
        void addRenderObject();
        void addRenderObjects(std::vector<sf::RectangleShape> render_objects);
        void render();
};