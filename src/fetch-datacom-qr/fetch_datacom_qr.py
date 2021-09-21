import json
import requests
from bs4 import BeautifulSoup

URL = 'https://www.avotechtv.com/'
ATTENDANCE_HISTORY_FILE = 'attendance_link_history.json'

def read_file(file):
    try:
        with open(file, 'r') as file_handler:
            data = json.load(file_handler)
        return data
    except FileNotFoundError:
        return []

def scrape_current_attendance_links():
    soup = BeautifulSoup(requests.get(URL).text, 'html.parser')

    current_attendance_links = []
    for attendance_qr_link in soup.find('section', {'id': 'comp-kpmcpsx17' }).find_all('div', {'class': '_31Ne5'}):
        label = attendance_qr_link.find('span', {'class': 'color_15'}).find('span').text
        download_link = attendance_qr_link.find('a', {'class': '_17W7D'}, href=True)
        if download_link is not None and download_link.has_attr('href'):
            download_link = download_link['href']
    
        current_attendance_links.append({ 'label': label, 'link': download_link })
    
    return current_attendance_links

def update_attendance():
    messages = []

    previous_attendance_links = read_file(ATTENDANCE_HISTORY_FILE)
    current_attendance_links = scrape_current_attendance_links()
    
    updated_attendance_links = []
    for current_attendance_link in current_attendance_links:
        if current_attendance_link not in previous_attendance_links:
            if not previous_attendance_links:
                messages.append(
                    'New Attendance QR found\n{}\n{}\n'.format(
                        current_attendance_link.get('label'), 
                        current_attendance_link.get('link')
                    )
                )
                updated_attendance_links.append(current_attendance_link)
            else:
                # Look for previous entries with the same label and update them accordingly. If none is found, just add it to the new list.
                for index, previous_attendance_link in enumerate(previous_attendance_links):
                    if current_attendance_link.get('label') == previous_attendance_link.get('label'):
                        messages.append(
                            'An update for {} is found. Updating the link from {} to {}'.format(
                                previous_attendance_link.get('label'), 
                                previous_attendance_link.get('link'), 
                                current_attendance_link.get('link')
                            )
                        )
                        previous_attendance_link['link'] = current_attendance_link['link']
                    elif index == (len(previous_attendance_links) - 1):
                        messages.append(
                            'New Attendance QR found\n{}\n{}\n'.format(
                                current_attendance_link.get('label'), 
                                current_attendance_link.get('link')
                            )
                        )
                        updated_attendance_links.append(current_attendance_link)

    updated_attendance_links = previous_attendance_links + updated_attendance_links

    with open(ATTENDANCE_HISTORY_FILE, 'w') as file_handler:
        json.dump(updated_attendance_links, file_handler, indent=4)

    return messages

def telegram_send_message():
    pass

if __name__ == '__main__':
    messages = update_attendance()

    for message in messages:
        print(message)
