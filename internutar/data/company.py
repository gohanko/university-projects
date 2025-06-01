from dataclasses import dataclass
from coordinator import Coordinator

@dataclass
class Company:
    name: str
    registration_number: str
    address: str
    city: str
    postcode: str
    province_state: str
    country: str
    postcode: str
    phone_number: str
    fax_number: str
    email: str
    website: str
    nature_of_business: str

    coordinator: Coordinator

    def _verify_email(self, email):
        return True

    def set_email(self, email):
        if not self._verify_email(email):
            return False
        
        self.email = email


