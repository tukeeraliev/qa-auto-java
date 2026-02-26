// tools/allure-screenshot.js
// Usage:
//   node tools/allure-screenshot.js allure-report allure-report.png

const { spawn, execSync } = require("child_process");
const path = require("path");
const { chromium } = require("playwright");

function kill(pid) {
  try {
    execSync(`taskkill /PID ${pid} /T /F`, { stdio: "ignore" });
  } catch {}
}

async function waitLoaded(page) {
  await page.waitForLoadState("networkidle");
  await page.waitForTimeout(2000);
}

async function run() {
  const reportDir = process.argv[2] || "allure-report";
  const outFile = process.argv[3] || "allure-report.png";

  const absDir = path.resolve(reportDir);

  console.log("Starting local server...");

  const server = spawn(
    "npx",
    ["http-server", absDir, "-p", "5055", "-c-1"],
    { shell: true }
  );

  const pid = server.pid;

  await new Promise(r => setTimeout(r, 4000));

  const url = "http://127.0.0.1:5055";

  console.log("Opening browser...");

  const browser = await chromium.launch();
  const page = await browser.newPage({ viewport: { width: 1920, height: 1080 } });

  await page.goto(url);
  await waitLoaded(page);

  await page.screenshot({ path: outFile, fullPage: true });

  await browser.close();

  console.log("Screenshot saved:", outFile);

  kill(pid);
  console.log("Server stopped");
}

run().catch(e => {
  console.error("ERROR:", e.message);
  process.exit(1);
});
