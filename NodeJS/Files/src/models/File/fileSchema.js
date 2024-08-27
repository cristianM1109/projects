import mongoose from "mongoose";
import {ObjectId} from "mongoose"
import path from "path"

export const fileSchema = mongoose.Schema(
    {
        model_type:{
            type:String,
            required:true
        },
        model_id:{
                type:ObjectId,
                required:true
        } ,
        name:{
            type:String,
            required:true
        },
        mimetype:{
            type:String,
            required:true
        },
        role:{
            type:String,
            default:"default"
        }
    },
    {
        methods:{
            getFolderPath:function(){
                return path.resolve() + "/media/" + this.model_type + "/"+ this.model_id + "/"
            },
            getPath: function(){
                return path.resolve() + "/media/" + this.model_type + "/"+ this.model_id + "/" + this.name
            }
        }
    }
)