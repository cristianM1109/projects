import mongoose from "mongoose";
import { ObjectId } from "mongodb";

export default commentSchema = mongoose.Schema({
    user_id:{
        type:ObjectId,
        required:true
    },
    post_id:{
        type:ObjectId,
        required:true
    },
    content:{
        type:String,
        required:true,
        minLength:[3,"Comment content must have at least 3 characters"],
        maxLength:[100,"Comment content must have maximum 100 characters"]
    }
})