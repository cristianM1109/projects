
import jwt from "jsonwebtoken"
import dotenv from "dotenv"
import bcrypt from "bcrypt"
import { responseError, responseSuccess } from "../middleware/utils/responseFormatting.js"
import { User } from "../models/User/userModel.js"
import { UserSettings } from "../models/userSettings/userSettingsModel.js"

/**
 * 
 * @param {Request} req 
 * @param {Response} res 
 * @returns 
 */

export const getALlUsers = async (req,res) => {
    
    let users = await User.find() 

    responseSuccess(res,users,200)
 
}

export const getUserById = async (req,res) =>{
    const id = req.params.id

    const user = await User.findById(id)
    responseSuccess(res,user,200)
    
}

export const createUser = async (req,res) =>{
    const name = req.body.name
    const email = req.body.email
    const password = req.body.password

    if (password != null && password.length < 8){
        responseError(res,"You must provide a stronger password")
        return
    }

    let salt = bcrypt.genSaltSync(10)
    const hash = bcrypt.hashSync(password,salt)

    const newUserSettings = new UserSettings()

    const newUser =  new User({
        settings_id:newUserSettings._id,
        name,email,
        password:hash
    }) 

    newUserSettings.user_id = newUser._id
    try{
        await newUser.save()
        await newUserSettings.save()
         responseSuccess(res,newUser,201)
    }catch(err){
        responseError(res,"The data provided in not valid!",err.errors,422)   
    }

}

export const updateUser = async (req,res) =>{
    const id = req.params.id
    const name = req.body.name
    const email = req.body.email

    let user = await User.findById(id)
    try{
        await User.updateOne(user,{name,email} , { runValidators:true })
        user = await User.findById(id)
        responseSuccess(res,user,201)
        }catch(err){
            responseError(res,"There is an error udating the user",err.errors,422)
        }
    
}

export const deleteUser = async (req,res) =>{
    const id = req.params.id

    const user = await User.findById(id)
    try{
    await User.deleteOne(user) 
    responseSuccess(res,user,200)
    }catch(err){
        responseError(res,"There has been an error",{},500)
    }
} 


export const logInUser = async (req,res) => {
    const email = req.body.email
    const password = req.body.password

    if (email == null || password == null) {
        responseError(res,"You must specify an email and a password",{},422)
    }

    let user = await User.find({email:email})

    if (user.length == 0){
        responseError(res,"Invalid credentials",{},401)

        return 
    }

    user = user[0]

    const passwordValid = bcrypt.compareSync(password,user.password)


    if (!passwordValid){
        responseError(res,"Invalid credentials",{},401)

        return 
    }
    const key = process.env.JWT_KEY

    const JWT = jwt.sign({email: user.email, name:user.name} , key , {expiresIn:3600*24*7})

    responseSuccess(res,JWT,200)
}

export const checkToken = async (req,res) =>{
    const token = req.body.token

    try{
    let result = jwt.verify(token,process.env.JWT_KEY)
    res.json({result})
    }catch(err){
        res.json({error:"invalid token"})
    }

}