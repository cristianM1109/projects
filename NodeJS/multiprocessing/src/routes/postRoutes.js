import { createPost, deletePost, getPost, getUserPost, updatesPost } from "../controllers/postRoutes.js"
import { modelExist } from "../middleware/modelExistsMiddleware.js"

export const registerPostRoutes = (app) => {
    //posts

    app.post("/users/:id/posts",createPost)
    app.get("/users/:id/posts",getUserPost)
    app.get("/posts",getPost)
    app.post("/users/posts/:postId",modelExist("Post","postId"),updatesPost)
    app.delete("/users/posts/:id",modelExist("Post") ,deletePost)

}