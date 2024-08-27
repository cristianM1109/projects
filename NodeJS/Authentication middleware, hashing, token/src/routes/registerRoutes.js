import {getALlUsers, createUser, getUserById, updateUser, deleteUser, logInUser, checkToken } from "./userRoutes.js"
import Express from "express"
import { authMiddleware } from "../middleware/authMiddleware.js"
/**
 * 
 * @param {Express} app 
 */

const registerRoutes = (app) => {
    //register all routes

    app.get("/users",authMiddleware,getALlUsers)
    app.get("/users/:id",authMiddleware,getUserById) 
    app.post("/users",authMiddleware,createUser)
    app.post("/users/login",logInUser) 
    app.patch("/users/:id",authMiddleware, updateUser)
    app.delete("/users/:id",authMiddleware,deleteUser)
    app.post("/check-token",authMiddleware,checkToken)

}

export {registerRoutes};