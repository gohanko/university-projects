from selenium.common.exceptions import NoSuchElementException

def unpack_columns_to_text(columns) -> list:
    processed_columns = []
    for column in columns:
        processed_columns.append(column.text.strip())

    return processed_columns

def parse_course_table(webdriver) -> list:
    rows = webdriver.find_elements_by_xpath('//div[@id=\'overviewSector\']/table/tbody/tr[position() > 1 and not (position() = last())]')
    parsed_data = []
    for row in rows:
        course_data = {}

        try:
            subheader = row.find_element_by_class_name('normalTbl-sub3header')
        except NoSuchElementException:
            course_data = parsed_data[-1]
            processed_columns = unpack_columns_to_text(row.find_elements_by_tag_name('td'))

            if len(processed_columns) == 11:
                course_data['data'].append({
                    'type': processed_columns[1],
                    'group': processed_columns[2],
                    'mode': processed_columns[3],
                    'class_size': processed_columns[4],
                    'day': [processed_columns[5]],
                    'time': [processed_columns[6]],
                    'hour': [processed_columns[7]],
                    'week': [processed_columns[8]],
                    'room': [processed_columns[9]],
                })
            elif len(processed_columns) == 5:
                course_data['data'][-1]['day'].append(processed_columns[0])
                course_data['data'][-1]['time'].append(processed_columns[1])
                course_data['data'][-1]['hour'].append(processed_columns[2])
                course_data['data'][-1]['week'].append(processed_columns[3])
                course_data['data'][-1]['room'].append(processed_columns[4])

            parsed_data[-1] = course_data
        else:
            course_data['code'] = subheader.text.split('-')[0].strip()
            course_data['data'] = []
            parsed_data.append(course_data)

    return parsed_data