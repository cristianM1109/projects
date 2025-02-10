import { test, expect } from "@playwright/test";

test("Auto-login and check invoices", async ({ page }) => {

  await page.goto("http://localhost:5173/login");

  await page.fill('input[type="email"]', "test1@user.com");
  await page.fill('input[type="password"]', "password");

  await page.click('button[type="submit"]');

  await page.waitForURL("http://localhost:5173/invoices");

  const title = await page.textContent("h2");
  expect(title).toBe("Invoices");

  const invoices = await page.$$("table tbody tr");
  expect(invoices.length).toBeGreaterThan(0);
});
