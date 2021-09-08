from selenium import webdriver

def setup_webdriver(browser):
    if browser == 'firefox':
        driver = webdriver.Firefox()
    elif browser == 'chrome':
        driver = webdriver.Chrome()
    else:
        # LOG: defaulting to Firefox.
        driver = webdriver.Firefox()

    return driver