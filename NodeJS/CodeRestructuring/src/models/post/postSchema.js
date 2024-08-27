import mongoose from "mongoose";
import { ObjectId } from "mongodb";

export const postSchema = mongoose.Schema({
    user_id:{
        type:ObjectId,
        required:true
    },
    title:{
        type:String,
        required:[true,"Post title is required"],
        minLength:[3,"Post title must have at least 3 characters"],
        maxLength:[30,"Post title must have maximum 30 characters"]
    },
    content:{
        type:String,
        required:[true,"Post mustr have some content"],
        minLength:[3,"Post content must have at least 3 characters"],
        maxLength:[100,"Post content must have maximum 100 characters"]
    }

})