import scrapy

class UTARIndustrialTrainingManagementPortalScraper(scrapy.Spider):
    name = 'UTAR Industrial Training Management Portal Scraper'
    start_urls = ['https://indtrng.utar.edu.my/companies.aspx']