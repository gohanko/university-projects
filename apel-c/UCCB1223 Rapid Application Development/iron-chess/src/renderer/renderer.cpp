#include "./renderer.h"

Renderer::Renderer(sf::RenderWindow * window) {
    m_window = window;
}

void Renderer::addRenderObjects(std::vector<sf::RectangleShape> render_objects) {
    m_render_objects = render_objects;
}

void Renderer::addSpriteObjects(std::vector<sf::Sprite> sprite_objects) {
    m_sprite_objects = sprite_objects;
}

void Renderer::render() {
    m_window->clear();

    for (const auto &render_object: m_render_objects) {
        m_window->draw(render_object);
    }

    for (const auto &sprite_object: m_sprite_objects) {
        m_window->draw(sprite_object);
    }

    m_window->display();
};
