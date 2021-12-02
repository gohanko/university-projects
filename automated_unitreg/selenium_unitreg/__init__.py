from selenium import webdriver

def setup_webdriver(browser):
    if browser == 'firefox':
        profile = webdriver.FirefoxProfile()
        profile.set_preference("general.useragent.override", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:94.0) Gecko/20100101 Firefox/94.0")
        driver = webdriver.Firefox(profile)
    elif browser == 'chrome':
        driver = webdriver.Chrome()
    else:
        # LOG: defaulting to Firefox.
        driver = webdriver.Firefox()

    return driver