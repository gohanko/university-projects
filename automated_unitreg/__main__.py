import json
import argparse
from automated_unitreg.selenium_unitreg import setup_webdriver
from automated_unitreg.selenium_unitreg.tasks import login, notify_when_course_available

LOGIN_URL = 'https://unitreg.utar.edu.my/portal/courseRegStu/login.jsp'

def parse_json_from_file(filename) -> dict:
    with open(filename) as file_handler:
        data = json.load(file_handler)
    return data

def parse_commands() -> None:
    parser = argparse.ArgumentParser()
    parser.add_argument('--browser', help='Specify which browser to use. Defaults to Firefox.')
    parser.add_argument('--username', help='Your student ID.', required=True)
    parser.add_argument('--password', help='Your password.', required=True)

    subparser = parser.add_subparsers(dest='command')

    camp_course_subparser = subparser.add_parser('camp_course', help='Camps a particular course in case someone lets go of it.')
    camp_course_subparser.add_argument('--course_code', help='Unit code of the course to camp.')

    automated_unitreg_subparser = subparser.add_parser('automated_unitreg', help='Automatically register for courses when time comes.')
    automated_unitreg_subparser.add_argument('--course_config', help='Specify the file containing the courses to be registered.')    

    args = parser.parse_args()
    webdriver = setup_webdriver(args.browser)

    try:
        if args.command == 'automated_unitreg':
            pass
        if args.command == 'camp_course':
            login(webdriver, LOGIN_URL, args.username, args.password)
            notify_when_course_available(webdriver, args.course_code)
    except (KeyboardInterrupt, SystemExit):
        webdriver.close()

if __name__ == '__main__':
    parse_commands()