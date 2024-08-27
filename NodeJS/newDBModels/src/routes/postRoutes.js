import { responseError, responseSuccess } from "../middleware/utils/responseFormatting.js"
import {Post} from "../models/Post/postModel.js"
import { ObjectId  } from "mongodb"
import { User } from "../models/User/userModel.js"

export const createPost = async (req,res) =>{
    const id = req.params.id
    const title = req.body.title
    const content = req.body.content

    const newPost = new Post({
        user_id:id,
        title,
        content
    })

    try{
        await newPost.save()
        responseSuccess(res,newPost,200)
    }catch(err){
        responseError(res,"Invalid data",err,422)
    }
}

export const getPost = async (req,res) => {
    try{
        let result = await Post.find()

        for(let i = 0;i<result.length;i++){
            result[i]._doc.user = await result[i].getUser()
        }
        

    responseSuccess(res,result)
    }catch(err){
        responseError(res,"There was an error getting the posts", err,500)
    }
}

export const updatesPost = async (req,res) =>{
    const id = req.params.id
    const title = req.body.title
    const content = req.body.content

    console.log(req.body)

    try{
        await Post.updateOne(
            {_id:new ObjectId(id)},
            {title,content},
            {runValidators:true}
        )
        const post = await Post.find({_id:new ObjectId(id)})
        responseSuccess(res,post,200)
    }catch(err){
        responseError(res,"Invalid data",err,422)
    }
}

export const deletePost = async (req,res) => {
    const id = req.params.id

    const post = await Post.findById(id)

    try{
        await Post.deleteOne(post)
        responseSuccess(res,post,200)
    }catch(err){
        responseError(res,"Could not delete post",err,500)
    }
}

export const getUserPost = async (req,res) => {
    const id = req.params.id

    const user = await User.findById(id)

    try{
        const result = await user.getPosts()
        responseSuccess(res,result)
    }catch(err){
        responseError(res,"There was an error getting the posts", err,500)
    }
}