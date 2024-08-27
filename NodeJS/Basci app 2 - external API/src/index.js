import express from "express" 
import axios from "axios"
import {getAllMovies,getMovie,updateMovie,createMovie,deleteMovie} from "./movieMethods.js"

const app = express()
const port = 3000  
const wheatherAPI = "https://api.open-meteo.com/v1/forecast"
app.use(express.json())


app.get("/movies",getAllMovies)
app.get("/movies/:id",getMovie)

app.patch("/movies/:id",updateMovie)
 
app.post("/movies",createMovie)

app.delete("/movies/:id",deleteMovie)

 app.get("/weather" ,async (req,res) =>{
    let data = {}

    try{
        let response = await axios.get(wheatherAPI, {
            params:{
                latitude: 45.46,
                longitude: 22.55,
                temperature_unit: "celsius",
                hourly:"temperature"
            }
        })

        data = response.data

        res.json(data)
    }catch(err){
        //error case
    } finally{
        //after any response
    }

   

    
})

// app.get("/weather" , (req,res) =>{
//     let data = {}

//     axios.get(wheatherAPI, {
//         params:{
//             latitude: 45.46,
//             longitude: 22.55,
//             temperature_unit: "celsius",
//             hourly:"temperature"
//         }
//     }).then((response) =>{
//         data = response.data
//         res.json(data)
//     }).catch(err =>{
//         //error case
//     }).finally(() =>{
//         //after any response
//     })

// })


app.listen(port,()=>{
    console.log("App started on port "+port)
})