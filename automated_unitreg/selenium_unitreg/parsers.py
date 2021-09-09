from selenium.common.exceptions import NoSuchElementException

# TODO: should parse the course table into a dict containing all relevant information.
def parse_course_table(webdriver):
    rows = webdriver.find_elements_by_xpath('//div[@id=\'overviewSector\']/table/tbody/tr[position() > 1 and not (position() = last())]')
    parsed_data = []
    for row in rows:
        course_data = {}

        try:
            subheader = row.find_element_by_class_name('normalTbl-sub3header')
        except NoSuchElementException:
            course_data = parsed_data[-1]
            tds = row.find_elements_by_tag_name('td')

            if len(tds) == 11:
                course_data['course_data'].append({
                    'type': tds[1].text,
                    'group': tds[2].text,
                    'mode': tds[3].text,
                    'class_size': tds[4].text,
                    'day': [tds[5].text],
                    'time': [tds[6].text],
                    'hour': [tds[7].text],
                    'week': [tds[8].text],
                    'room': [tds[9].text],
                })
            elif len(tds) == 5:
                course_data['course_data'][-1]['day'].append(tds[0].text)
                course_data['course_data'][-1]['time'].append(tds[1].text)
                course_data['course_data'][-1]['hour'].append(tds[2].text)
                course_data['course_data'][-1]['week'].append(tds[3].text)
                course_data['course_data'][-1]['room'].append(tds[4].text)

            parsed_data[-1] = course_data
        else:
            course_data['course_code'] = subheader.text.split('-')[0]
            course_data['course_data'] = []
            parsed_data.append(course_data)

    return parsed_data