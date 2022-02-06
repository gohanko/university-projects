#include <vector>
#include <SFML\Graphics.hpp>

class Renderer {
    private:
        sf::RenderWindow * m_window;
        std::vector<sf::RectangleShape> m_render_objects;
    public:
        Renderer(sf::RenderWindow * window) {
            m_window = window;
        }

        void addRenderObject(sf::RectangleShape render_object) {
            m_render_objects.push_back(render_object);
        };

        void addRenderObjects(std::vector<sf::RectangleShape> render_objects) {
            m_render_objects = render_objects;
        }

        void render() {
            m_window->clear();

            for (const auto &render_object: m_render_objects) {
                m_window->draw(render_object);
            }

            m_window->display();
        };
};