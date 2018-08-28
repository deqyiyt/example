$(function(){
	//实例化编辑器
	var um = UM.getEditor('content',{
		UMEDITOR_HOME_URL: ias.config.umeditorHomeUrl
		,imageUrl: ias.config.uploadImgUrl //图片上传提交地址
		,imagePath: ias.config.downloadUrl //图片下载地址，引用了fixedImagePath,如有特殊需求，可自行配置
		,imageFieldName: "file" //图片数据的key,若此处修改，需要在后台对应文件修改对应参数
		,toolbar: [
	        'source | undo redo | bold italic underline strikethrough | emotion image video | superscript subscript formula | forecolor backcolor | removeformat |',
	        'insertorderedlist insertunorderedlist | selectall cleardoc | paragraph fontsize',
	        '| justifyleft justifycenter justifyright justifyjustify |',
	        'link unlink ',
	        '| horizontal print preview fullscreen'
	    ]
		,autoClearEmptyNode:true
	    ,autoHeightEnabled:false
	    ,initialFrameWidth:"100%"
	    ,initialFrameHeight:"500"
	    ,enterTag:""
	    ,pasteplain:false
	    ,focus:true
	    ,zIndex: 10052
	    ,allHtmlEnabled:true
	    ,autoFloatEnabled:false
	});
	um.addListener('afterfullscreenchange',function(){
		console.log(um.$container);
	});
});