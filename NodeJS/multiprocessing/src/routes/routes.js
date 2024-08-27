import { longFunction, shortFunction } from "../controllers/testController.js"
import { registerFileRoutes } from "./fileRoutes.js"
import { registerPostRoutes } from "./postRoutes.js"
import { registerUserRoutes } from "./userRoutes.js"
import { registerSettingsRoutes } from "./userSettings.Routes.js"

export const registerRoutes = (app) => {
    registerFileRoutes(app)
    registerPostRoutes(app)
    registerUserRoutes(app)
    registerSettingsRoutes(app)

    app.get("/test-long",longFunction)
    app.get("/test-short",shortFunction)

}