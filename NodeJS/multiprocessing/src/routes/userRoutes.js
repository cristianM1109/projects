import { checkToken, createUser, deleteUser, getALlUsers, getUserById, logInUser, updateUser } from "../controllers/userRoutes.js"
import { authMiddleware } from "../middleware/authMiddleware.js"

export const registerUserRoutes = (app) => {
    // user routes
    app.get("/users",authMiddleware,getALlUsers)
    app.get("/users/:id",authMiddleware,getUserById) 
    app.post("/users",authMiddleware,createUser)
    app.post("/users/login",logInUser) 
    app.patch("/users/:id",authMiddleware, updateUser)
    app.delete("/users/:id",authMiddleware,deleteUser)
    app.post("/check-token",authMiddleware,checkToken)
}