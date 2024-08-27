import {getALlUsers, createUser, getUserById, updateUser, deleteUser, logInUser, checkToken } from "./userRoutes.js"
import Express from "express"
import { authMiddleware } from "../middleware/authMiddleware.js"
import { getUserSettings, updateUserSettings } from "./userSettingsController.js"
import { createPost, deletePost, getPost, getUserPost, updatesPost } from "./postRoutes.js"
import { postExist } from "../middleware/postExistsMiddleware.js"
import { modelExist } from "../middleware/modelExistsMiddleware.js"
import { createFile, downloadFile, downloadFileByModel } from "./fileController.js"
/**
 * 
 * @param {Express} app 
 */

const registerRoutes = (app) => {
    //register all routes

    

     

    
    

}

export {registerRoutes};