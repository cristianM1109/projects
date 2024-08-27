


const http = require("http");
const server = http.createServer((request,response) =>{
  console.log(`Received request for ${request.url}`);
  response.writeHead(200,{'Content-Type':'Text/plain'});
  response.write('Hello,world! sent from node.js');
  response.end();
});
server.listen(3000, ()=>{
  console.log('Server started at port 3000');
})