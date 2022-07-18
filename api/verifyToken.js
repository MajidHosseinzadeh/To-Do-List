const jwt = require("jasonwebtoken");

function verify(req, res, next){
    const authHeader = req.headers.token;
    if (authHeader){
        const token = authHeader.split(" ")[1]
        jwt.verify(token, process.env.SECRET_KEY, (err, user) =>{
            if (err) (res.status(403).json("not valid token"));

            req.user = user; 
        })
    }else{
        return res.status(401).json("fuck");
    }
}

module.exports = verify; 