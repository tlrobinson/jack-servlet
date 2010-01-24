exports.app = function(env) {
    return {
        status : 200,
        headers : {"Content-Type" : "text/plain"},
        body : ["It works!"]
    };
}
