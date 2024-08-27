import { responseError, responseSuccess } from "../middleware/utils/responseFormatting.js"
import { UserSettings } from "../models/userSettings/userSettingsModel.js"
import { ObjectId } from "mongodb"

export const getUserSettings = async (req,res) =>{
    const id = req.params.id

    const result = (await UserSettings.find({user_id: new ObjectId(id)}))[0]

    responseSuccess(res,result,200)
}

export const updateUserSettings = async (req,res) =>{
    const id = req.params.id

    const darkMode = req.body.darkMode
    const profile_private = req.body.profile_private
    const two_factor_auth_enabled = req.body.two_factor_auth_enabled

    try{
        await UserSettings.updateOne(
            {user_id: new ObjectId(id)},
            {
            darkMode,
            profile_private,
            two_factor_auth_enabled
            },
            {runValidators:true}
        )
        const result = (await UserSettings.find({user_id: new ObjectId(id)}))[0]
        responseSuccess(res,result,200)
    }catch(err){
        responseError(res,"Invalid data",err)
    }

    
    
}

