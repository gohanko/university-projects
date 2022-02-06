#include "renderer.h"
#include <iostream>

Renderer::Renderer(sf::RenderWindow * window) {
    m_window = window;
}

void Renderer::addRenderObject(sf::RectangleShape render_object) {
    m_render_objects.push_back(render_object);
};

void Renderer::addRenderObjects(std::vector<sf::RectangleShape> render_objects) {
    m_render_objects = render_objects;
}

void Renderer::render() {
    m_window->clear();

    for (const auto &render_object: m_render_objects) {
        m_window->draw(render_object);
    }

    m_window->display();
};
