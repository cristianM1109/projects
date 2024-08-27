import { responseSuccess } from "../middleware/utils/responseFormatting.js"
import { UserSettings } from "../models/userSettings/userSettingsModel.js"
import { ObjectId } from "mongodb"

export const getUserSettings = async (req,res) =>{
    const id = req.params.id

    const result = (await UserSettings.find({_user_id: new ObjectId(id)}))[0]

    responseSuccess(res,result,200)
}