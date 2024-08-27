const formatResponse = (data) => {
    let responseData={
        data:data == undefined ? null : data
    }
    return responseData
}

const formatErrorResponse = (text) =>{
    let responseData={
        error:text
    }
    return responseData
}

export {formatResponse,formatErrorResponse}