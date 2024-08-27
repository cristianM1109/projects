import express from "express";
import mongoose from "mongoose";
import dotenv from "dotenv";
import expressFileUpload from "express-fileupload";
import { registerRoutes } from "./routes/routes.js";
import cluster from "node:cluster"
import process from "node:process"
    


if (cluster.isPrimary){
    console.log(`Primary processId: ${process.pid}`)
    let workers = []
    for(let i = 0; i < 3;i++){
        let worker = cluster.fork() 
        worker.addListener("orice", () => {
            console.log("Orice event occured!")
            workers.push(cluster.fork())
        })
    worker.addListener("destroy", () =>{
        worker.kill()
    })
    workers.push(worker)
    }
}else{
    let app = express();
    let port = 3000;

    dotenv.config();
    let mongoConnectionURL = `mongodb+srv://${process.env.MONGO_DB_USER}:${process.env.MONGO_DB_PASS}@${process.env.MONGO_DB_URL}/?retryWrites=true&w=majority&appName=Cluster0`;

    app.use(express.json());
    app.use(expressFileUpload());

    registerRoutes(app)

    app.listen(port, async () => {
        try {
          await mongoose.connect(mongoConnectionURL, { dbName: "test" });
        } catch (err) {
          throw err;
        }
        // let users = await User.find()
        // console.log(users)
        console.log(`App started on port: ${port} [porcess Id: ${process.pid}]` );
      });
}


