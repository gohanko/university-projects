import os
import unittest
from selenium import webdriver
from automated_unitreg.selenium_unitreg.parsers import parse_course_table, unpack_columns_to_text

class TestParsers(unittest.TestCase):
    def setUp(self) -> None:
        self.driver = webdriver.Firefox()

    def test_unpack_columns_to_text(self) -> None:
        """Must return processed list in the same order."""
        pass

    def test_parse_course_table(self) -> None:
        '''Data must be in the correct column etc.'''
        self.driver.get('file://{}//data/fixtures/course_table.html'.format(os.getcwd()))
        data = parse_course_table(self.driver)

        self.assertEqual(data[0]['code'], 'NNNN01')

    def tearDown(self) -> None:
        self.driver.close()
