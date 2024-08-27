import path from "path"
import fs from "fs"
import { responseError, responseSuccess } from "../middleware/utils/responseFormatting.js"
import { File } from "../models/File/fileModel.js"
import mongoose from "mongoose"
import { ObjectId } from "mongodb"

export const  createFile = async (req,res) =>{
    const myFile = req.files.my_File
    const modelType = req.body.modelType
    const modelId = req.body.model_id
    const role = req.body.role


    if (modelType == null || modelId == null){
        responseError(res, "You must specify a model_type and model_id")
        return 
    }

    try{
        const model = mongoose.model(modelType)
        const matchingModelCount = (await model.find({ _id:new ObjectId(modelId) })).length
        if (matchingModelCount != 1){
            responseError(res,"Matching model count is different than 1")
            return 
        }
    }catch(err){
        responseError(res,"Model does not exist")
        return 
    }
    const fileName = myFile.md5 + "_" + myFile.name
    
    const file = new File({
        model_type:modelType,
        model_id:modelId,
        name:fileName,
        mimetype:myFile.mimetype 
    })

    if (!fs.existsSync(file.getFolderPath())){
        fs.mkdirSync(file.getFolderPath(),{recursive:true})
    }

    // if (fs.existsSync(folderPath)){
    //     responseError(res,"file aready exists")
    //     return
    // } 

    myFile.mv(file.getPath(), async (err) =>{
        if(err){
            throw err
        }
    })

    if (role != null){
        file.role = role
    }

    try{
        await file.save()
        responseSuccess(res,file,201)
    }catch(err){
        fs.unlinkSync(filePath)
        responseError(res,"Cannot upload file",err)
    }
    
}



export const downloadFile = async (req,res) => {

    const fileId = req.params.id

    const file = await File.findById(fileId)

    if (file == null){
        responseError(res,"No file found",404)
        return
    }
    
    const fileContent = fs.readFileSync(file.getPath(),{encoding:"base64"})

    responseSuccess(res,{...file._doc,file_content:fileContent})

}


export const downloadFileByModel = async (req,res) => {

    const modelType = req.body.model_type
    const modelId = req.body.model_id

    let files = await File.find({model_type:modelType,model_id:modelId})

    if (files.length == 0){
        responseError(res,"No file found",404)
        return
    }

    files = files.map(file => {
        const fileContents = fs.readFileSync(file.getPath(),{encoding:"base64"})
        return {
            ...files._doc,
            file_content:fileContents
        }
    })
    

    responseSuccess(res,files)

}