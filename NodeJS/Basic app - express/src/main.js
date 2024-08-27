const express = require('express')
const app = express()
const port = 3000


app.use(express.json())

let sendResponse = (response,statusCode,contents,message = null ) =>{
    
    let status = "error"
    if(statusCode <300){
        status = "ok"
    }

    let responseData = {
        status:status,
        message,
        body:contents
    }
    
    response.status(statusCode).json(responseData)
}

app.get('/',(req,res) =>{
    sendResponse(res,200,{},'Hello World!')
})

// function myFunction(){
//     console.log('Test')
// }

// let myFunction2 = function(){
//     console.log('Test2')
// }

// let myFunction3 = () =>{
//     console.log('Test3')
// }

app.get('/test',(request,response) =>{
    sendResponse(response,200,{},'new endpoint')
})

app.post('/test-post',(request,response) =>{
    let data = request.body
    console.log(request)
    sendResponse(response,201,data,'new endpoint2')
})

app.post('/age-verification',(request,response) =>{
    let age = request.body.age
    response.status(201)
    if (age <18 )
        sendResponse(response,201,{},'you are not of legal age,you cannot gamble')
    else
        sendResponse(response,201,{},'you are of legal age,Welcome!')
})


app.all('*',(req,res) =>{
    sendResponse(res,404,{},'URL not found!')
})

app.listen(port,()=>{
    console.log(`Exemple app listen to port ${port}`)
})