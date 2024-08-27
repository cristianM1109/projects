import mongoose from "mongoose"
import {Post} from "../models/Post/postModel.js"
import { responseError } from "./utils/responseFormatting.js"

export const postExist = async (req,res,next) => {
    const id = req.params.id 

    if (id == null){
        throw new Error("Route does not have an ID parameter")
    }
    
    const postCount = await mongoose.model("Post").find({ _id:id }).length

    if (postCount < 1){
        responseError(res,"Not found!",null,404)
        return
    }

    next()

}