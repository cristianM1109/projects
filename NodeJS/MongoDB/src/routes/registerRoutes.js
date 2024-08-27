import {getALlUsers, createUser, getUserById, updateUser, deleteUser } from "./userRoutes.js"
import Express from "express"
/**
 * 
 * @param {Express} app 
 */

const registerRoutes = (app) => {
    //register all routes

    app.get("/users",getALlUsers)
    app.get("/users/:id",getUserById) 
    app.post("/users",createUser)
    app.patch("/users/:id", updateUser)
    app.delete("/users/:id",deleteUser)

}

export {registerRoutes};