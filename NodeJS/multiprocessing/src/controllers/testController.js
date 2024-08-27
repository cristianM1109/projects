import cluster from "node:cluster"

export const longFunction = (req,res) => {
    for (let index = 0; index < 6000000000; index++){

    }

    cluster.worker.emit("orice")

    res.json({done:true}) 
}

export const shortFunction = (req,res) => {
    res.json({done:true})
}