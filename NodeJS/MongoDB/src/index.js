import express from "express"
import mongoose from "mongoose"
import dotenv from "dotenv"
import User from "./models/User/userModel.js"
import {registerRoutes} from "./routes/registerRoutes.js"
let app = express()
let port = 3000

dotenv.config()
let mongoConnectionURL = `mongodb+srv://${process.env.MONGO_DB_USER}:${process.env.MONGO_DB_PASS}@${process.env.MONGO_DB_URL}/?retryWrites=true&w=majority&appName=Cluster0`

app.use(express.json())

registerRoutes(app)

app.listen(port, async () =>{
    try{
    await mongoose.connect(mongoConnectionURL, {dbName:"test"})
    }catch(err){
        throw err
    }
    // let users = await User.find()
    // console.log(users)
    console.log("App started on port "+port)
    
});

 














