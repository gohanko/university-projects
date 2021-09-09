import pprint
import os
import unittest
from selenium import webdriver
from automated_unitreg.selenium_unitreg.parsers import parse_course_table

class TestParsers(unittest.TestCase):
    def setUp(self) -> None:
        self.driver = webdriver.Firefox()

    def test_parse_course_table(self) -> None:
        self.driver.get('file://{}//data/fixtures/course_table.html'.format(os.getcwd()))
        data = parse_course_table(self.driver)
        pprint.pprint(data, indent=4)

    def tearDown(self) -> None:
        self.driver.close()