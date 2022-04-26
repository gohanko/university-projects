import time, sys
from selenium import webdriver
from plyer import notification

BASE_URL = 'https://unitreg.utar.edu.my'
URL_PATHS = {
    'login': '/portal/courseRegStu/login.jsp',
}

ERROR_MESSAGES = {
    'bad_username_or_password': '',
    'bad_captcha': '32 Invalid code'
}

driver = webdriver.Firefox()

def fill_input_by_name(element_name, input):
    element = driver.find_element_by_name(element_name)
    element.clear()
    element.send_keys(input)

def click_button_by_name(element_name):
    element = driver.find_element_by_name(element_name)
    element.click()

def login(username, password):
    driver.get('{}{}'.format(BASE_URL, URL_PATHS['login']))
    assert "Welcome to Course Registration System" in driver.title
    fill_input_by_name('reqFregkey', username)
    fill_input_by_name('reqPassword', password)
    fill_input_by_name('kaptchafield', input('Enter the captcha code you see: '))
    click_button_by_name('_submit')

def camp_course(course_code):
    my_course_registration_elem = driver.find_element_by_link_text('My Course Registration')
    my_course_registration_elem.click()

    click_button_by_name('Register')

    course_input_elem = driver.find_element_by_id('reqUnit')
    course_input_elem.clear()
    course_input_elem.send_keys(course_code)

    while True:
        click_button_by_name('Save')
        availabilities = driver.find_elements_by_xpath('//div/table/tbody/tr[position() mod 3 = 2]/td[12]')
        for availability in availabilities:
            if availability.text != '0':
                notification.notify(title='NEW AVAILABILITY!', message='New availability for course {}'.format(course_code))
                sys.exit(0)

        time.sleep(1)

if __name__ == '__main__':
    with open('camp_data.txt', 'r') as camp_data_file:
        data = camp_data_file.readlines()

    login(username=data[0], password=data[1])
    camp_course(data[2])