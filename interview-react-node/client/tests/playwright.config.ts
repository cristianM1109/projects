import { defineConfig } from "@playwright/test";

export default defineConfig({
  use: {
    headless: true, 
    baseURL: "http://localhost:5173",
    browserName: "chromium", 
  },
  testDir: "./tests",
});
