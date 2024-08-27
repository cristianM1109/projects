import { createFile, downloadFile, downloadFileByModel } from "../controllers/fileController.js"

export const registerFileRoutes = (app) => {
    //files
    app.post("/file",createFile)
    app.get("/file/:id",downloadFile)
    app.get("/files-by-model",downloadFileByModel)
}