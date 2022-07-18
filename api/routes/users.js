const router = require("express").Router();
const User = require("../models/User");
const CryptoJS = require("crypto-js");
const verify = require("../verifyToken")

router.put("/:id", verify, async (req, res) => {
    if (req.user.id === req.params.id || req.use.isAdmin) {
        if (req.body.password) {
            req.body.password = CryptoJS.AES.encrypt(
                req.body.password,
                process.env.SECRET_KEY
            ).toString();
        }

        try {
            const updatedUser = await User.findByIdAndUpdate(req.params.id,
                {
                    $set: req.body,
                },
                {
                    new: true
                }
            );
            res.status(200).json(updatedUser)
        } catch (e) {
            res.status(500).json(e)
        }
    } else {
        req.status(403).json("you can update only your account!")
    }
});

router.delete("/:id", verify, async (req, res) => {
    if (req.user.id === req.params.id || req.use.isAdmin) {  
        try {
            await User.findByIdAndDelete(req.params.id);
            res.status(200).json("user deleted ")
        } catch (e) {
            res.status(500).json(e)
        }
    } else {
        req.status(403).json("you can delete  only your account!")
    }
});

router.get ("/find/:id", async (req, res) => {
        try {
            const user = await User.findById(req.params.id);
            const { password, ...info } = user._doc;
            res.status(200).json(info)
        } catch (e) {
            res.status(500).json(e)
        }
    }
);

router.get("/?new:true", verify, async (req, res) => {
    const query = req.query.new;
    if (req.use.isAdmin) {  
        try {
            const users = query ? await User.find().sort({_id:-1 }).limit(10) : await User.find( );
            res.status(200).json(users)
        } catch (e) {
            res.status(500).json(e)
        }
    } else {
        req.status(403).json("you are not allowed")
    }
});

router.get("/stats", async (req, res) => {
    const today = new Date();
    const latYear = today.setFullYear(today.setFullYear() - 1);
  
    try {
      const data = await User.aggregate([
        {
          $project: {
            month: { $month: "$createdAt" },
          },
        },
        {
          $group: {
            _id: "$month",
            total: { $sum: 1 },
          },
        },
      ]);
      res.status(200).json(data)
    } catch (err) {
      res.status(500).json(err);
    }
  });

module.exports = router; 