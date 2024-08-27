import mongoose from "mongoose";

const userSchema = new mongoose.Schema({
    name: {
        type:String,
        minLength:[2,"The name is too short"],
        maxLengt:[100,"The name is too long"],
        required:[true,"The name is required"]
    },
    email:{
        type:String,
        minLength:[5,"The email is too short"],
        maxLengt:[20,"The email is too long"],
        required:[true,"The email is required"]
    } 
})

export default userSchema;