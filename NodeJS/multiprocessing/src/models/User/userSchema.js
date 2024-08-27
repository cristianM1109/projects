import mongoose from "mongoose";
import { ObjectId } from "mongodb";

const userSchema = new mongoose.Schema(
    {
    name: {
        type:String,
        minLength:[2,"The name is too short"],
        maxLengt:[100,"The name is too long"],
        required:[true,"The name is required"]
    },
    email:{
        type:String,
        minLength:[5,"The email is too short"],
        unique:true,
        maxLengt:[20,"The email is too long"],
        required:[true,"The email is required"]
    },
    password:{
        type:String,
        minLength:[5,"The password is too short"],
        maxLength:[200,"The password is too long"],
        required:[true,"The password is required"]
    },
    settings_id:{
        type:ObjectId,
        required:true
    }
    },
    {

        methods:{
            getPosts: async function(){
                return await mongoose.model("Post").find({user_id:this._id})
            }
        }
    }
)

export default userSchema;