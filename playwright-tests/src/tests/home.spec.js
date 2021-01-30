const playwright = require('playwright');

const PAGE_URL = "http://localhost:8081";

for (const browserType of ["chromium", "firefox"]) {
    describe(`(${browserType}): Home page tests with Playwright`, () => {
        let browser = null;
        let page = null;

        beforeAll(async () => {
            browser = await playwright[browserType].launch();
            page = await browser.newPage();

            if (!page) {
                throw new Error("Connection wasn't established");
            }
        }, 10000);

        beforeEach(async () => {
            await page.goto(PAGE_URL);
            await page.click("text=LogOut");
            await page.goto(PAGE_URL, {waitUntil: "networkidle0"});
        });

        afterAll(async () => {
            await browser.close();
        });

        test(`(${browserType}): Should load page`, async () => {
            expect(page).not.toBeNull();
            expect(await page.title()).not.toBeNull();
        });

        test(`(${browserType}): Check text at home page`, async () => {
            const paragraph = await page.$("text=Home page");
            expect(paragraph).not.toBeNull();
        });
    });
}
