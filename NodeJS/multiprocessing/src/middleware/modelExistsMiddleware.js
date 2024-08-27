import mongoose from "mongoose"
import {Post} from "../models/Post/postModel.js"
import { responseError } from "./utils/responseFormatting.js"

export const modelExist = (modelname,urlParamName = "id") =>{
    const model = mongoose.model(modelname)
    return async (req,res,next) => {
        const id = req.params[urlParamName]
    
        if (id == null){
            throw new Error("Route does not have an ID parameter")
        }
        


        const modelCount = (await mongoose.model(modelname).find({_id:id})).length
        if (modelCount < 1){
            responseError(res,"Not found!",null,404)
            return
        }
    
        next()
    
    }
}