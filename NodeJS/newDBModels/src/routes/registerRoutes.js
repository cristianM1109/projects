import {getALlUsers, createUser, getUserById, updateUser, deleteUser, logInUser, checkToken } from "./userRoutes.js"
import Express from "express"
import { authMiddleware } from "../middleware/authMiddleware.js"
import { getUserSettings, updateUserSettings } from "./userSettingsRoutes.js"
import { createPost, deletePost, getPost, getUserPost, updatesPost } from "./postRoutes.js"
import { postExist } from "../middleware/postExistsMiddleware.js"
import { modelExist } from "../middleware/modelExistsMiddleware.js"
/**
 * 
 * @param {Express} app 
 */

const registerRoutes = (app) => {
    //register all routes

    // user routes
    app.get("/users",authMiddleware,getALlUsers)
    app.get("/users/:id",authMiddleware,getUserById) 
    app.post("/users",authMiddleware,createUser)
    app.post("/users/login",logInUser) 
    app.patch("/users/:id",authMiddleware, updateUser)
    app.delete("/users/:id",authMiddleware,deleteUser)
    app.post("/check-token",authMiddleware,checkToken)

    //user settings

    app.get("/users/:id/settings",getUserSettings)
    app.post("/users/:id/settings",updateUserSettings)

    //posts

    app.post("/users/:id/posts",createPost)
    app.get("/users/:id/posts",getUserPost)
    app.get("/posts",getPost)
    app.post("/users/posts/:postId",modelExist("Post","postId"),updatesPost)
    app.delete("/users/posts/:id",modelExist("Post") ,deletePost)


}

export {registerRoutes};