import mongoose from "mongoose";
import { ObjectId } from "mongodb";

export const userSettingSchema = mongoose.Schema({
    user_id:{
        type:ObjectId,
        required:true
    },
    darkMode:{
        type:Boolean,
        default:false
    },
    profile_private:{
        type:Boolean,
        default:false,
    },
    two_factor_auth_enabled:{
        type:Boolean,
        default:false
    }
})