const multer = require('multer');
var express = require('express');
var router = express.Router();
const imageSavePath = '../public/images/';

const storage = multer.diskStorage({
    //파일저장경로 
    destination(req, file, callback) {
        callback(null, __dirname + '/' + imageSavePath)
    },
    //저장되는 파일이름 형식 커스텀 가능 
    filename(req, file, callback) {
        callback(null, file.originalname)
    }
});
const upload = multer({ storage: storage });

router.post('', upload.single('upLoadImage'), async (req, res, next) => {
    console.log(req.file);// 파일정보나옴
    res.send('return success');
});


/* GET users listing. */
router.get('/', function (req, res, next) {
    res.send('respond with a resource');
});

module.exports = router;