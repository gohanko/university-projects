def fill_input_by_name(webdriver, element_name, input) -> None:
    element = webdriver.find_element_by_name(element_name)
    element.clear()
    element.send_keys(input)

def click_button_by_name(webdriver, element_name) -> None:
    element = webdriver.find_element_by_name(element_name)
    element.click()