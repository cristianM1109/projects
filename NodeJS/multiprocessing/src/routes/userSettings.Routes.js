import { getUserSettings, updateUserSettings } from "../controllers/userSettingsController.js";

export const registerSettingsRoutes = (app) => {
  //user settings

  app.get("/users/:id/settings", getUserSettings);
  app.post("/users/:id/settings", updateUserSettings);
};
