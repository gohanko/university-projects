import sys
import time
from plyer import notification
from automated_unitreg.selenium_unitreg.helper import fill_input_by_name, click_button_by_name

def login(webdriver, login_url, username, password) -> None:
    webdriver.get(login_url)
    assert "Welcome to Course Registration System" in webdriver.title
    fill_input_by_name('reqFregkey', username)
    fill_input_by_name('reqPassword', password)
    fill_input_by_name('kaptchafield', input('Enter the captcha code you see: '))
    click_button_by_name('_submit')

def notify_when_course_available(webdriver, course_code) -> None:
    my_course_registration_elem = webdriver.find_element_by_link_text('My Course Registration')
    my_course_registration_elem.click()

    click_button_by_name('Register')

    course_input_elem = webdriver.find_element_by_id('reqUnit')
    course_input_elem.clear()
    course_input_elem.send_keys(course_code)

    while True:
        click_button_by_name('Save')
        availabilities = webdriver.find_elements_by_xpath('//div/table/tbody/tr[position() mod 3 = 2]/td[12]')
        for availability in availabilities:
            if availability.text != '0':
                notification.notify(title='NEW AVAILABILITY!', message='New availability for course {}'.format(course_code))
                sys.exit(0)

        time.sleep(1)