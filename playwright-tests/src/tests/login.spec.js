const playwright = require('playwright');

const PAGE_URL = "http://localhost:8081/login";

for (const browserType of ["chromium", "firefox"]) {
    describe(`(${browserType}): Login page tests with Playwright`, () => {
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

        test(`(${browserType}): Check form fields and button`, async () => {
            expect(await page.$("#username")).not.toBeNull();
            expect(await page.$("#password")).not.toBeNull();
            expect(await page.$("button >> text=Login")).not.toBeNull();
        });

        test(`(${browserType}): Check auth`, async () => {
            await page.fill('#username', 'admin');
            await page.fill('#password', 'password');
            await page.click('button >> text=Login');
            await page.waitForNavigation();
            expect(await page.$("text=admin")).not.toBeNull();
        });

        test(`(${browserType}): Check auth with keyboard`, async () => {
            await page.fill('#username', 'admin');
            await page.fill('#password', 'password');
            await page.keyboard.press('Tab');
            await page.keyboard.press('Enter');
            await page.waitForNavigation();
            expect(await page.$("text=admin")).not.toBeNull();
        });
    });
}
