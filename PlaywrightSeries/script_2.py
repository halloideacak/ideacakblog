from playwright.sync_api import sync_playwright
from playwright.sync_api import expect

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False)
    page = browser.new_page()
    page.goto("http://localhost:8000/index.html")
    print(page.title())
    
    page.get_by_role("button", name="Sign in").click()
    expect(page.get_by_text("Welcome, John")).to_be_visible()
    
    input("enter to close")
    browser.close()