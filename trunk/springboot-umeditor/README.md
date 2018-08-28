## java集成百度umeditor文件上传

### 上传文件返回值如果自定义的话，需要修改2个js文件
1、修改截图后，ctrl + v 上传文件， 第30行ajax返回值解析
/plugins/umeditor/plugins/autoupload.js

2、本地文件上传， 第188行ajax返回值解析
/plugins/umeditor/dialogs/image/image.js